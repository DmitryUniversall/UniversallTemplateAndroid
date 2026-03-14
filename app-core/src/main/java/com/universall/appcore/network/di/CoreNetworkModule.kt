package com.universall.appcore.network.di

import com.universall.appcore.network.CoreHttpClientConfigHolder
import com.universall.appcore.network.di.annotations.CoreHttpClientConfig
import com.universall.appcore.serialization.di.annotations.CoreJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClientConfig
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {
    @Provides
    @Singleton
    @CoreHttpClientConfig
    fun provideCoreConfig(@CoreJson jsonCore: Json): HttpClientConfig<*>.() -> Unit {
        return CoreHttpClientConfigHolder.coreHttpClientConfig(jsonCore)
    }
}
