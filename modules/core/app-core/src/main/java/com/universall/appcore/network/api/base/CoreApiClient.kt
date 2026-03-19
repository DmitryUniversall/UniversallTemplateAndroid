package com.universall.appcore.network.api.base

import com.universall.appcore.network.api.base.middleware.ApiClientMiddlewareChain
import io.ktor.client.request.HttpRequestBuilder

abstract class CoreApiClient {  // TODO: Refactor it!
    abstract fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>>

    suspend fun <T> request(
        contextBuilder: ApiRequestContext.Builder? = null,
        build: HttpRequestBuilder.() -> Unit
    ): ApiResponseContext<T> {
        val context = (contextBuilder ?: ApiRequestContext.Builder()).build()

        val builder = HttpRequestBuilder()
        builder.apply(build)

        val chain = setupMiddlewareChain<T>().build()
        return chain.execute(builder, context)
    }
}
