package com.universall.auth_impl.domain.usecases

import com.universall.auth_api.domain.entities.User
import com.universall.auth_api.domain.repositories.UsersRepository
import com.universall.auth_api.domain.usecases.GetMeUseCase
import jakarta.inject.Inject

internal class GetMeUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : GetMeUseCase {
    override suspend fun invoke(): Result<User> {
        return usersRepository.getMe()
    }
}
