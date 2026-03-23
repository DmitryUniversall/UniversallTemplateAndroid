package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.repositories.SessionsRepository
import com.universall.auth_api.domain.usecases.RevokeAllSessionsExceptCurrentUseCase
import jakarta.inject.Inject

internal class RevokeAllSessionsExceptCurrentUseCaseImpl @Inject constructor(
    private val sessionsRepository: SessionsRepository
) : RevokeAllSessionsExceptCurrentUseCase {
    override suspend fun invoke(): Result<Unit> {
        return sessionsRepository.revokeAllSessionsExceptCurrent()
    }
}
