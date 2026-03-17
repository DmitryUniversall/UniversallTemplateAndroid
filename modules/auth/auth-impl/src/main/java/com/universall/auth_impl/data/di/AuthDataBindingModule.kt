package com.universall.auth_impl.data.di

import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.repositories.SessionsRepository
import com.universall.auth_api.domain.repositories.UsersRepository
import com.universall.auth_impl.data.repositories.AuthRepositoryImpl
import com.universall.auth_impl.data.repositories.SessionsRepositoryImpl
import com.universall.auth_impl.data.repositories.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataBindingModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSessionsRepository(impl: SessionsRepositoryImpl): SessionsRepository

    @Binds
    @Singleton
    abstract fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}
