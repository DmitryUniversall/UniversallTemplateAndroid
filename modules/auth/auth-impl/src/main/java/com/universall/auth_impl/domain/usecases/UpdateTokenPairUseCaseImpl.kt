package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.entities.LocalAuthInfo
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.UpdateTokenPairUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class UpdateTokenPairUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : UpdateTokenPairUseCase {
    override suspend fun invoke(tokenPair: AuthTokenPair): Result<Unit> {
        val state = authRepository.authStateFlow.first()

        if (state is AuthState.Authenticated) {
            authRepository.setAuthState(state.copy(tokenPair = tokenPair), syncLocal = true)
        } else {
            authRepository.setLocalAuthInfo(LocalAuthInfo(accessToken = tokenPair.accessToken, refreshToken = tokenPair.refreshToken))
        }

        return Result.success(Unit)
    }
}
