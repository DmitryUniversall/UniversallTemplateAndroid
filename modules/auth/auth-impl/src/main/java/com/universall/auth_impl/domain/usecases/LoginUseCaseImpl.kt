package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.usecases.LoginUseCase
import jakarta.inject.Inject

internal class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LoginUseCase {
    override suspend fun invoke(schema: LoginSchema): Result<Pair<AuthContext, AuthTokenPair>> {
        return authRepository.login(schema).onSuccess { data ->
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
