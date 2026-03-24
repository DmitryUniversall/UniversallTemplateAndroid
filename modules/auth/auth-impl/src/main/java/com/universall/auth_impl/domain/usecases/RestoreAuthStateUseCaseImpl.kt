package com.universall.auth_impl.domain.usecases

import com.universall.appcore.network.exceptions.InvalidAuthHttpException
import com.universall.appcore.utils.logInfo
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.RefreshUseCase
import com.universall.auth_api.domain.usecases.RestoreAuthStateUseCase
import com.universall.core.utils.messageOrDefault
import jakarta.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

internal class RestoreAuthStateUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val refreshUseCase: RefreshUseCase
) : RestoreAuthStateUseCase {
    private fun isRefreshNeeded(error: Throwable): Boolean = error is InvalidAuthHttpException

    override suspend fun invoke(): Result<AuthState> {
        this.logInfo { "Restoring auth state" }

        val authInfo = authRepository.getLocalAuthInfo()
        if (authInfo == null) {
            this.logInfo { "No local auth info found; Unauthenticated" }
            authRepository.setAuthState(AuthState.Unauthenticated)
            return Result.success(AuthState.Unauthenticated)
        }

        this.logInfo { "Found local auth info; Fetching auth context" }
        var tokenPair = authInfo.asAuthTokenPair()

        return authRepository.getCurrentAuthContext(tokenPair.accessToken)  // Try to fetch auth context
            // Failed, try to refresh
            .recoverCatching { error ->
                if (error is CancellationException || !isRefreshNeeded(error)) throw error

                this.logWarn { "Failed to fetch auth context using local tokens, refreshing: ${error::class.simpleName}: ${error.message}" }
                tokenPair = refreshUseCase.invoke().getOrThrow()

                authRepository.getCurrentAuthContext(tokenPair.accessToken).getOrThrow()
            }
            // Success (context fetched instantly or after token refresh). Map result context into Authenticated state
            .map { authContext ->
                this.logInfo { "Successfully fetched auth context using local tokens; Authenticated" }

                AuthState.Authenticated(
                    context = authContext,
                    tokenPair = tokenPair
                )
            }
            // Treat any other error as non-auth error and set TemporarilyUnauthenticated state
            .recoverCatching { error ->
                AuthState.TemporarilyUnauthenticated(
                    reason = error.messageOrDefault("Unknown auth error"),
                    error = error
                )
            }
            // Finally update auth state
            .onSuccess { newState ->
                authRepository.setAuthState(newState, syncLocal = true)
            }
    }
}
