package com.universall.core.network.api

import com.universall.core.network.api.middleware.ApiClientMiddlewareChain
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.serialization.serializer

abstract class CoreApiClient {
    abstract fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>>

    suspend inline fun <reified T> request(
        contextBuilder: ApiRequestContext.Builder? = null,
        crossinline build: HttpRequestBuilder.() -> Unit
    ): ApiResponseContext<T> {
        // TODO: Move it to ApiClient
        // TODO: CoreApiClient should not know about ApiResponseParseMiddleware and its requirements
        val context = (contextBuilder ?: ApiRequestContext.Builder())
            .setDefaultMeta("serializer", serializer<T>())
            .build()

        val builder = HttpRequestBuilder()
        builder.apply(build)

        val chain = setupMiddlewareChain<T>().build()
        return chain.execute(builder, context)
    }

    suspend inline fun <reified T> requestDataNotNull(
        crossinline build: HttpRequestBuilder.() -> Unit
    ): T {
        return request<T>(build = build).apiResponse.data!!
    }
}
