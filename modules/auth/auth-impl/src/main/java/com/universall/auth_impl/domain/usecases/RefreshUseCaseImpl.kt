package com.universall.auth_impl.domain.usecases

import com.universall.appcore.appcore.network.exceptions.InvalidAuthHttpException
import com.universall.appcore.appcore.logs.logWarn
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.GetTokenPairUseCase
import com.universall.auth_api.domain.usecases.RefreshUseCase
import com.universall.auth_api.domain.usecases.UpdateTokenPairUseCase
import com.universall.core.exceptions.UnauthenticatedAppError
import com.universall.core.utils.messageOrDefault
import jakarta.inject.Inject
import kotlinx.coroutines.sync.Mutex

internal class RefreshUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val getTokenPairUseCase: GetTokenPairUseCase,
    private val updateTokenPairUseCase: UpdateTokenPairUseCase
) : RefreshUseCase {
    companion object {
        private val refreshMutex = Mutex()
    }

    private fun isPermanentAuthFailure(error: Throwable): Boolean = error is InvalidAuthHttpException

    override suspend operator fun invoke(): Result<AuthTokenPair> = runCatching {
        if (!refreshMutex.tryLock()) {  // Try to acquire lock
            // Failed. Someone already trying to refresh tokens
            refreshMutex.lock()
            refreshMutex.unlock()

            val tokens = getTokenPairUseCase.invoke().getOrThrow()

            // If tokens are still available, we assume that they are correct
            // It means that other refresh call finished with success or non-auth error
            return@runCatching tokens ?: throw UnauthenticatedAppError("Refresh tokens failed")
        }

        // Lock acquired, refresh tokens
        try {
            val tokens = getTokenPairUseCase.invoke().getOrThrow() ?: throw UnauthenticatedAppError("App does not have refresh token")

            return authRepository.refresh(tokens.refreshToken)
                .onSuccess { tokenPair -> updateTokenPairUseCase.invoke(tokenPair) }
                .onFailure { error ->
                    this.logWarn(error) { "Failed to refresh auth tokens" }

                    // Server literally told us that user has to log in again
                    if (isPermanentAuthFailure(error)) {
                        authRepository.setAuthState(AuthState.Unauthenticated, syncLocal = true)
                        return@onFailure
                    }

                    // Unknown error happened; This doesn't mean that tokens are invalid (can be network error)
                    authRepository.setAuthState(AuthState.TemporarilyUnauthenticated(reason = error.messageOrDefault("Failed to refresh auth tokens"), error = error))
                }
        } finally {
            refreshMutex.unlock()
        }
    }
}
