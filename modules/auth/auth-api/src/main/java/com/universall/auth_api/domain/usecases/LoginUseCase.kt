package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.schemas.LoginSchema

interface LoginUseCase {
    suspend fun login(
        schema: LoginSchema
    ): Result<Pair<AuthContext, AuthTokenPair>>
}
