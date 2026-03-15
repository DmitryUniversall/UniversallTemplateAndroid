package com.universall.auth_api.domain.usecases

interface LogoutUseCase {
    suspend operator fun invoke(): Result<Unit>
}
