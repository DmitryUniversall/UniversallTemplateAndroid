package com.universall.auth_api.domain.repositories

import com.universall.auth_api.domain.entities.User

interface UsersRepository {
    suspend fun getMe(): Result<User>
}
