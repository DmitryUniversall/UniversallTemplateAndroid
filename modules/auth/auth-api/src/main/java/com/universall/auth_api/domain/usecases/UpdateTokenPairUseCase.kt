package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthTokenPair

interface UpdateTokenPairUseCase {
    suspend operator fun invoke(tokenPair: AuthTokenPair): Result<Unit>
}
