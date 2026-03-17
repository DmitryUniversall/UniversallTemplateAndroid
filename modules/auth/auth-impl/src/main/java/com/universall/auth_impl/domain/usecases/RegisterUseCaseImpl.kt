package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_api.domain.usecases.RegisterUseCase
import jakarta.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : RegisterUseCase {
    override suspend fun invoke(schema: RegisterSchema): Result<Pair<AuthContext, AuthTokenPair>> {
        return authRepository.register(schema)
    }
}
