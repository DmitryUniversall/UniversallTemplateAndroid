package com.universall.auth_impl.domain.usecases

import com.universall.appcore.network.exceptions.AuthExpiredHttpException
import com.universall.appcore.utils.logInfo
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.RefreshUseCase
import com.universall.auth_api.domain.usecases.RestoreAuthStateUseCase
import jakarta.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RestoreAuthStateUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val refreshUseCase: RefreshUseCase
) : RestoreAuthStateUseCase {
    // TODO: Add mutex
    // TODO: Refactor (use try-catch?)
    override suspend fun invoke(): Result<AuthState> {
        this.logInfo { "Restoring auth state" }

        val authInfo = authRepository.getLocalAuthInfo()
        if (authInfo == null) {
            this.logInfo { "No local auth info found; Unauthenticated" }
            authRepository.setAuthState(AuthState.Unauthenticated)
            return Result.success(AuthState.Unauthenticated)
        }

        var accessToken = authInfo.accessToken
        var refreshToken = authInfo.refreshToken

        this.logInfo { "Found local auth info; Fetching auth context" }

        return authRepository.getCurrentAuthContext(accessToken)  // Try to fetch auth context
            // Failed, try to refresh
            .recoverCatching { error ->
                if (error is CancellationException) throw error

                // AuthExpiredHttpException can be thrown during AppCodeProcessingPolicy call
                if (!isRefreshNeeded(error)) throw error

                this.logWarn { "Failed to fetch auth context using local tokens, refreshing: ${error::class.simpleName}: ${error.message}" }
                val tokenPair = refreshUseCase.invoke(refreshToken).getOrThrow()
                accessToken = tokenPair.accessToken
                refreshToken = tokenPair.refreshToken
                authRepository.getCurrentAuthContext(accessToken).getOrThrow()
            }
            // Success (context fetched instantly or after token refresh). Map result context into Authenticated state
            .map { authContext ->
                this.logInfo { "Successfully fetched auth context using local tokens; Authenticated" }

                AuthState.Authenticated(
                    context = authContext,
                    tokenPair = AuthTokenPair(
                        accessToken = accessToken,
                        refreshToken = refreshToken
                    )
                )
            }

            // Failed (context was NOT fetched at all)
            // This is basically onFailure
            .recoverCatching { error ->
                if (error is CancellationException) throw error

                this.logWarn { "Failed to fetch auth context using local tokens: ${error::class.simpleName}: ${error.message}" }

                // Server literally told us that user has to log in again
                if (isPermanentAuthFailure(error)) return@recoverCatching AuthState.Unauthenticated

                // Unknown error happened; This doesn't mean that tokens are invalid (can be network error)
                AuthState.TemporarilyUnauthenticated(reason = error.message ?: "Failed to fetch auth context")
            }

            // Finally, update auth state
            .onSuccess { newState ->
                authRepository.setAuthState(newState, syncLocal = true)
            }
    }

    private fun isRefreshNeeded(error: Throwable): Boolean {
        @Suppress("IMPOSSIBLE_IS_CHECK_WARNING", "KotlinConstantConditions")
        return error is AuthExpiredHttpException
    }

    private fun isPermanentAuthFailure(error: Throwable): Boolean {
        @Suppress("IMPOSSIBLE_IS_CHECK_WARNING", "KotlinConstantConditions")
        return error is AuthExpiredHttpException
    }
}
