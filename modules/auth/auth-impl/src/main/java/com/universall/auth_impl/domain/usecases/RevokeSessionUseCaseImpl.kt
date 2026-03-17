package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.repositories.SessionsRepository
import com.universall.auth_api.domain.usecases.RevokeSessionUseCase
import jakarta.inject.Inject

class RevokeSessionUseCaseImpl @Inject constructor(
    private val sessionsRepository: SessionsRepository
) : RevokeSessionUseCase {
    override suspend fun invoke(sessionUUID: String): Result<Unit> {
        return sessionsRepository.revokeSession(sessionUUID)
    }
}
