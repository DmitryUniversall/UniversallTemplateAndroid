package com.universall.appcore.network.api

import com.universall.appcore.entities.ApiResponse
import com.universall.appcore.network.api.middleware.ApiClientMiddlewareChain
import com.universall.appcore.network.api.middleware.generics.ApiResponseParseMiddleware
import com.universall.appcore.network.api.middleware.generics.AppCodeProcessingMiddleware
import com.universall.appcore.network.api.middleware.generics.KtorExecuteMiddleware
import com.universall.appcore.network.api.middleware.generics.RetryRequestMiddleware
import com.universall.appcore.policies.app_code.AppCodeProcessingPolicy
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

open class CoreApiClient(
    protected val httpClient: HttpClient,
    protected val json: Json,
    protected val appCodeProcessingPolicy: AppCodeProcessingPolicy,
    protected val retryCount: Int
) {
    open fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain<ApiResponseContext<T>> {
        return ApiClientMiddlewareChain.Builder<ApiResponseContext<T>>()
            .add(KtorExecuteMiddleware(httpClient))
            .add(RetryRequestMiddleware(retryCount))
            .add(ApiResponseParseMiddleware<T>(json))
            .add(AppCodeProcessingMiddleware(appCodeProcessingPolicy))
            .build()
    }

    suspend inline fun <reified T> request(
        contextBuilder: ApiRequestContext.Builder? = null,
        crossinline build: HttpRequestBuilder.() -> Unit
    ): ApiResponse<T> {
        val context = (contextBuilder ?: ApiRequestContext.Builder())
            .setDefaultMeta("serializer", serializer<T>())
            .build()

        val builder = HttpRequestBuilder()
        builder.apply(build)

        val chain = setupMiddlewareChain<T>()
        val response = chain.execute(builder, context)
        return response.apiResponse
    }

    suspend inline fun <reified T> requestDataNotNull(
        crossinline build: HttpRequestBuilder.() -> Unit
    ): T {
        return request<T>(build = build).data!!
    }
}
