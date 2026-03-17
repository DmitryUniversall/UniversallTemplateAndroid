package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.GetTokenPairUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first

class GetTokenPairUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetTokenPairUseCase {
    override suspend fun invoke(): Result<AuthTokenPair?> = runCatching {
        val state = authRepository.authStateFlow.first()
        if (state is AuthState.Authenticated) return@runCatching state.tokenPair
        return@runCatching authRepository.getLocalAuthInfo()?.asAuthTokenPair()
    }
}
