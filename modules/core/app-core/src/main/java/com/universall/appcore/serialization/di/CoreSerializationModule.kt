package com.universall.appcore.serialization.di

import com.universall.appcore.serialization.CoreJsonHolder
import com.universall.appcore.serialization.di.qualifiers.CoreJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreSerializationModule {
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
