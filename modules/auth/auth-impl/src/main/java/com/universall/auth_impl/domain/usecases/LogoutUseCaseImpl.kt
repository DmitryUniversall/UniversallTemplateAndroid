package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.repositories.SessionsRepository
import com.universall.auth_api.domain.usecases.LogoutUseCase
import jakarta.inject.Inject

internal class LogoutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionsRepository: SessionsRepository
) : LogoutUseCase {
    override suspend fun invoke(): Result<Unit> = runCatching {
        sessionsRepository.revokeCurrentSession().getOrThrow()
        authRepository.setAuthState(AuthState.Unauthenticated)
    }
}
