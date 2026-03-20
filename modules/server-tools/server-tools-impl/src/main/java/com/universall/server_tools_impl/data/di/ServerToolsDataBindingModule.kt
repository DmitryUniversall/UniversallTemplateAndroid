package com.universall.server_tools_impl.data.di

import com.universall.server_tools_api.domain.repositories.ServerToolsRepository
import com.universall.server_tools_impl.data.repositories.ServerToolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServerToolsDataBindingModule {
    @Binds
    @Singleton
    abstract fun bindServerToolsRepository(impl: ServerToolsRepositoryImpl): ServerToolsRepository
}
