package com.universall.appcore.appcore.network.ktor

import com.universall.appcore.BuildConfig
import com.universall.appcore.appcore.network.ktor.plugins.KtorResponseLoggerPlugin
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal typealias HttpClientConfigBlock = @JvmSuppressWildcards HttpClientConfig<*>.() -> Unit

internal object CoreKtorClientHolder {
    fun buildCoreKtorHttpClientConfig(json: Json): HttpClientConfigBlock = {
        install(KtorResponseLoggerPlugin.plugin)

        install(ContentNegotiation) {
            json(json)
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

    fun buildCoreKtorHttpClient(config: HttpClientConfigBlock): HttpClient {
        return HttpClient(config)
    }
}
