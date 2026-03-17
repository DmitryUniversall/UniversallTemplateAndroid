package com.universall.auth_api.domain.usecases

import com.universall.auth_api.domain.entities.User

interface GetMeUseCase {
    suspend operator fun invoke(): Result<User>
}
