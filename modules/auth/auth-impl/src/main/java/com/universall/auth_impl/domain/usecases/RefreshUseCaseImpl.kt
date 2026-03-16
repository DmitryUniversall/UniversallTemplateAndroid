package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.usecases.RefreshUseCase
import jakarta.inject.Inject

class RefreshUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : RefreshUseCase {
    override suspend operator fun invoke(
        refreshToken: String
    ): Result<AuthTokenPair> {}
}
