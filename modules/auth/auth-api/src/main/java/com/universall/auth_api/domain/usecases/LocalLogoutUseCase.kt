package com.universall.auth_api.domain.usecases

interface LocalLogoutUseCase {
    suspend operator fun invoke(): Result<Unit>
}
