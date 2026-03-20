package com.universall.server_tools_impl.domain.di

import com.universall.server_tools_api.domain.usecases.PingServerUseCase
import com.universall.server_tools_impl.domain.usecases.PingServerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServerToolsDomainBindingModule {
    @Binds
    @Singleton
    abstract fun bindPingServerUseCase(impl: PingServerUseCaseImpl): PingServerUseCase
}
