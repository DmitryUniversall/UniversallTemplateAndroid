package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.User

interface FetchMeUseCase {
    suspend operator fun invoke(): Result<User>
}
