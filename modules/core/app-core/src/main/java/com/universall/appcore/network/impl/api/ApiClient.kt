package com.universall.appcore.network.impl.api

import com.universall.appcore.network.impl.api.base.ApiResponseContext
import com.universall.appcore.network.impl.api.base.CoreApiClient
import com.universall.appcore.network.impl.api.base.middleware.ApiClientMiddlewareChain
import com.universall.appcore.network.impl.api.middleware.ApiResponseParseMiddleware
import com.universall.appcore.network.impl.api.middleware.AppCodeProcessingMiddleware
import com.universall.appcore.network.impl.api.middleware.KtorExecuteMiddleware
import com.universall.appcore.network.impl.api.middleware.RetryRequestMiddleware
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

open class ApiClient(
    protected val httpClient: HttpClient,
    protected val json: Json,
    protected val retryCount: Int
) : CoreApiClient() {
    override fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>> {
        return ApiClientMiddlewareChain.Builder<ApiResponseContext<T>>()
            .add(KtorExecuteMiddleware(httpClient))
            .add(RetryRequestMiddleware(retryCount))
            .add(ApiResponseParseMiddleware<T>(json))
            .add(AppCodeProcessingMiddleware())
    }
}
