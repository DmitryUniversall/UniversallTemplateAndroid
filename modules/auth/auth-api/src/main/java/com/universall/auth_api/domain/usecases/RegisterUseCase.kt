package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.schemas.RegisterSchema

interface RegisterUseCase {
    suspend operator fun invoke(
        schema: RegisterSchema
    ): Result<Pair<AuthContext, AuthTokenPair>>
}
