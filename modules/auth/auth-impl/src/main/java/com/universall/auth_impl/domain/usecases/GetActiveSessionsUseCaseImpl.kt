package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthSession
import com.universall.auth_api.domain.repositories.SessionsRepository
import com.universall.auth_api.domain.usecases.GetActiveSessionsUseCase
import jakarta.inject.Inject

internal class GetActiveSessionsUseCaseImpl @Inject constructor(
    private val sessionsRepository: SessionsRepository
) : GetActiveSessionsUseCase {
    override suspend operator fun invoke(): Result<List<AuthSession>> {
        return sessionsRepository.getActiveSessions()
    }
}
