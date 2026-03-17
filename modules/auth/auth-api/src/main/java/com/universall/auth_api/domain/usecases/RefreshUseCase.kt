package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthTokenPair

interface RefreshUseCase {
    suspend operator fun invoke(): Result<AuthTokenPair>
}
