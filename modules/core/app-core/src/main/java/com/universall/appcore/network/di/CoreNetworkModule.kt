package com.universall.appcore.network.di

import com.universall.appcore.BuildConfig
import com.universall.appcore.network.api.ApiClient
import com.universall.appcore.network.di.qualifiers.CoreHttpClient
import com.universall.appcore.network.di.qualifiers.CoreHttpClientConfig
import com.universall.appcore.network.ktor.plugins.KtorResponseLoggerPlugin
import com.universall.appcore.serialization.di.qualifiers.CoreJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

typealias HttpClientConfigBlock = @JvmSuppressWildcards HttpClientConfig<*>.() -> Unit

@Module
@InstallIn(SingletonComponent::class)
internal object CoreNetworkModule {
    @Provides
    @Singleton
    @CoreHttpClientConfig
    fun provideCoreHttpClientConfig(@CoreJson jsonCore: Json): HttpClientConfigBlock {
        return {
            install(KtorResponseLoggerPlugin.plugin)

            install(ContentNegotiation) {
                json(jsonCore)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10_000
                connectTimeoutMillis = 10_000
                socketTimeoutMillis = 10_000
            }

            defaultRequest {
                url(BuildConfig.API_URL)
                contentType(ContentType.Application.Json)
            }
        }
    }

    @Provides
    @Singleton
    @CoreHttpClient
    fun provideCoreHttpClient(@CoreHttpClientConfig config: HttpClientConfigBlock): HttpClient {
        return HttpClient(config)
    }

    @Provides
    @Singleton
    fun provideHttpClient(@CoreHttpClient client: HttpClient): HttpClient {
        return client
    }

    @Provides
    @Singleton
    fun provideApiClient(
        @CoreHttpClient httpClient: HttpClient,
        @CoreJson json: Json,
    ): ApiClient {
        return ApiClient(
            httpClient = httpClient,
            json = json
        )
    }
}
