package com.universall.auth_api.domain.usecases

interface RevokeAllSessionsExceptCurrentUseCase {
    suspend operator fun invoke(): Result<Unit>
}
