package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.AuthSession

interface GetActiveSessionsUseCase {
    suspend operator fun invoke(): Result<List<AuthSession>>
}
