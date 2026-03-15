package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.schemas.RegisterSchema

interface RegisterUseCase {
    suspend operator fun invoke(
        shema: RegisterSchema
    ): Result<AuthState.Authenticated>
}
