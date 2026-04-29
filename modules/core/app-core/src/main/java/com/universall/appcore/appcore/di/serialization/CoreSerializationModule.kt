package com.universall.appcore.appcore.di.serialization

import com.universall.appcore.appcore.di.serialization.qualifiers.CoreJson
import com.universall.appcore.appcore.serialization.CoreJsonHolder
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
