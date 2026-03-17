package com.universall.appcore.network.di

import com.universall.appcore.BuildConfig
import com.universall.appcore.network.impl.api.ApiClient
import com.universall.appcore.network.di.annotations.CoreHttpClient
import com.universall.appcore.network.di.annotations.CoreHttpClientConfig
import com.universall.appcore.network.impl.ktor.plugins.KtorResponseLoggerPlugin
import com.universall.appcore.serialization.di.annotations.CoreJson
import com.universall.core.network.api.CoreApiClient
import com.universall.core.policies.app_code.AppCodeProcessingPolicy
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

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {
    @Provides
    @Singleton
    @CoreHttpClientConfig
    fun provideCoreConfig(@CoreJson jsonCore: Json): HttpClientConfig<*>.() -> Unit {
        return {
            install(KtorResponseLoggerPlugin.plugin)

            install(ContentNegotiation) {
                json(jsonCore)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
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
    fun provideCoreHttpClient(@CoreHttpClientConfig config: HttpClientConfig<*>.() -> Unit): HttpClient {
        return HttpClient(config)
    }

    @Provides
    @Singleton
    fun provideCoreApiClient(
        @CoreHttpClient httpClient: HttpClient,
        @CoreJson json: Json,
        appCodeProcessingPolicy: AppCodeProcessingPolicy
    ): CoreApiClient {
        return ApiClient(
            httpClient = httpClient,
            json = json,
            appCodeProcessingPolicy = appCodeProcessingPolicy,
            retryCount = BuildConfig.NETWORK_REQUEST_RETRY_COUNT
        )
    }
}
