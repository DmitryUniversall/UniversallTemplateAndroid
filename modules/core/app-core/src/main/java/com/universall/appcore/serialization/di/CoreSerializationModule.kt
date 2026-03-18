package com.universall.appcore.serialization.di

import com.universall.appcore.serialization.di.annotations.CoreJson
import com.universall.appcore.serialization.impl.CoreJsonHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreSerializationModule {
    @Provides
    @Singleton
    @CoreJson
    fun provideCoreJson(): Json {
        return CoreJsonHolder.json
    }

    @Provides
    @Singleton
    fun provideJson(@CoreJson json: Json): Json = json
}
