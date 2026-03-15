package com.universall.auth_api.domain.usecases

interface RevokeSessionUseCase {
    suspend operator fun invoke(
        sessionUUID: String
    ): Result<Unit>
}
