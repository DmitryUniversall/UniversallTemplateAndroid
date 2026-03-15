package com.universall.appcore.network

import com.universall.appcore.BuildConfig
import com.universall.appcore.network.plugins.KtorResponseLoggerPlugin
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object CoreHttpClientConfigHolder {
    fun coreHttpClientConfig(jsonCore: Json): HttpClientConfig<*>.() -> Unit {
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
}
