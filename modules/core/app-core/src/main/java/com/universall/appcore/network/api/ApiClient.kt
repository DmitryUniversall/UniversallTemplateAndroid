package com.universall.appcore.network.api

import com.universall.appcore.network.api.base.ApiRequestContext
import com.universall.appcore.network.api.base.ApiResponseContext
import com.universall.appcore.network.api.base.CoreApiClient
import com.universall.appcore.network.api.base.middleware.ApiClientMiddlewareChain
import com.universall.appcore.network.api.middleware.ApiResponseParseMiddleware
import com.universall.appcore.network.api.middleware.AppCodeProcessingMiddleware
import com.universall.appcore.network.api.middleware.KtorExecuteMiddleware
import com.universall.appcore.network.api.middleware.RetryRequestMiddleware
import com.universall.appcore.network.di.qualifiers.CoreHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

open class ApiClient(
    protected val httpClient: HttpClient,
    protected val json: Json,
) : CoreApiClient() {
    override fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>> {
        return ApiClientMiddlewareChain.Builder<ApiResponseContext<T>>()
            .add(KtorExecuteMiddleware(httpClient))
            .add(RetryRequestMiddleware())
            .add(ApiResponseParseMiddleware<T>(json))
            .add(AppCodeProcessingMiddleware())
    }

    suspend inline fun <reified T> requestData(
        contextBuilder: ApiRequestContext.Builder? = null,
        noinline build: HttpRequestBuilder.() -> Unit
    ): ApiResponseContext<T> {
        val contextBuilder = (contextBuilder ?: ApiRequestContext.Builder())
            .setDefaultMeta("dataSerializer", serializer<T>())

        return this.request(contextBuilder, build)
    }

    suspend inline fun <reified T> requestDataObject(
        contextBuilder: ApiRequestContext.Builder? = null,
        noinline build: HttpRequestBuilder.() -> Unit
    ): T {
        return requestData<T>(contextBuilder, build).apiResponse.data!!
    }

    suspend inline fun requestUnit(
        contextBuilder: ApiRequestContext.Builder? = null,
        noinline build: HttpRequestBuilder.() -> Unit
    ): ApiResponseContext<Unit> {
        val contextBuilder = (contextBuilder ?: ApiRequestContext.Builder())
            .setDefaultMeta("skipDataSerialization", true)

        return request(contextBuilder, build)
    }
}
