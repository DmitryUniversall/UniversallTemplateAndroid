package com.universall.appcore.appcore.di.network

import com.universall.appcore.appcore.network.api.ApiClientHolder
import com.universall.appcore.appcore.network.ktor.KtorApiCallTransport
import com.universall.appcore.base.network.api.ApiCallTransport
import com.universall.appcore.base.network.api.ApiClient
import com.universall.appcore.base.network.api.AppCodeProcessingPolicy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    fun provideApiCallTransport(httpClient: HttpClient): ApiCallTransport {
        return KtorApiCallTransport(httpClient)
    }

    @Provides
    @Singleton
    fun provideApiClient(
        transport: ApiCallTransport,
        json: Json,
        appCodeProcessingPolicy: AppCodeProcessingPolicy,
    ): ApiClient {
        return ApiClientHolder.buildApiClient(
            transport = transport,
            interceptors = listOf(),
            json = json,
            appCodeProcessingPolicy = appCodeProcessingPolicy
        )
    }
}
