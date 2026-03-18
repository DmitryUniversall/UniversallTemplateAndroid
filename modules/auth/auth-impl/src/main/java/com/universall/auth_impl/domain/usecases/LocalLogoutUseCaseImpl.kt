package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.LocalLogoutUseCase
import jakarta.inject.Inject

class LocalLogoutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LocalLogoutUseCase {
    override suspend fun invoke(): Result<Unit> = runCatching {
        authRepository.setAuthState(AuthState.Unauthenticated, syncLocal = true)
    }
}
