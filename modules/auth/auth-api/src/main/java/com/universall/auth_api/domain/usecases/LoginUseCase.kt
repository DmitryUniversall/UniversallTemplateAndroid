package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.schemas.LoginSchema

interface LoginUseCase {
    suspend fun login(
        schema: LoginSchema
    ): Result<AuthState.Authenticated>
}
