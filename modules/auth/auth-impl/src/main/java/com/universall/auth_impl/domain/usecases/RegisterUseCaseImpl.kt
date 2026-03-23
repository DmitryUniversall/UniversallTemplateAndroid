package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_api.domain.usecases.RegisterUseCase
import jakarta.inject.Inject

internal class RegisterUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : RegisterUseCase {
    override suspend fun invoke(schema: RegisterSchema): Result<Pair<AuthContext, AuthTokenPair>> {
        return authRepository.register(schema).onSuccess { data ->
            authRepository.setAuthState(
                AuthState.Authenticated(
                    context = data.first,
                    tokenPair = data.second
                ),
                syncLocal = true
            )
        }
    }
}
