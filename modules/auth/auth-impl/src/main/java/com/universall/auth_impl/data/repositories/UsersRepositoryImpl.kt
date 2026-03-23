package com.universall.auth_impl.data.repositories

import com.universall.auth_api.domain.entities.User
import com.universall.auth_api.domain.repositories.UsersRepository
import com.universall.auth_impl.data.sources.UsersNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UsersRepositoryImpl @Inject constructor(
    private val networkDataSource: UsersNetworkDataSource
) : UsersRepository {
    override suspend fun getMe(): Result<User> {
        return networkDataSource.getMe().map { dto -> dto.user.toEntity() }
    }
}
