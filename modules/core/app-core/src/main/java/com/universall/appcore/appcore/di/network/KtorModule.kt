package com.universall.appcore.appcore.di.network

import com.universall.appcore.appcore.di.network.qualifiers.CoreHttpClient
import com.universall.appcore.appcore.di.network.qualifiers.CoreHttpClientConfig
import com.universall.appcore.appcore.di.serialization.qualifiers.CoreJson
import com.universall.appcore.appcore.network.ktor.CoreKtorClientHolder
import com.universall.appcore.appcore.network.ktor.HttpClientConfigBlock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object KtorModule {
    @Provides
    @Singleton
    @CoreHttpClientConfig
    fun provideCoreHttpClientConfig(@CoreJson jsonCore: Json): HttpClientConfigBlock {
        return CoreKtorClientHolder.buildCoreKtorHttpClientConfig(jsonCore)
    }

    @Provides
    @Singleton
    @CoreHttpClient
    fun provideCoreHttpClient(@CoreHttpClientConfig config: HttpClientConfigBlock): HttpClient {
        return CoreKtorClientHolder.buildCoreKtorHttpClient(config)
    }

    @Provides
    @Singleton
    fun provideHttpClient(@CoreHttpClient client: HttpClient): HttpClient = client
}
