package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthState

interface RestoreAuthStateUseCase {
    suspend operator fun invoke(): Result<AuthState>
}
