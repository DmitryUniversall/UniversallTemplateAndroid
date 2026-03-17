package com.universall.appcore.network.impl.api

import com.universall.appcore.network.impl.api.middleware.ApiResponseParseMiddleware
import com.universall.appcore.network.impl.api.middleware.AppCodeProcessingMiddleware
import com.universall.appcore.network.impl.api.middleware.KtorExecuteMiddleware
import com.universall.appcore.network.impl.api.middleware.RetryRequestMiddleware
import com.universall.core.network.api.ApiResponseContext
import com.universall.core.network.api.CoreApiClient
import com.universall.core.network.api.middleware.ApiClientMiddlewareChain
import com.universall.core.policies.app_code.AppCodeProcessingPolicy
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

open class ApiClient(
    protected val httpClient: HttpClient,
    protected val json: Json,
    protected val appCodeProcessingPolicy: AppCodeProcessingPolicy,
    protected val retryCount: Int
) : CoreApiClient() {
    override fun <T> setupMiddlewareChain(): ApiClientMiddlewareChain.Builder<ApiResponseContext<T>> {
        return ApiClientMiddlewareChain.Builder<ApiResponseContext<T>>()
            .add(KtorExecuteMiddleware(httpClient))
            .add(RetryRequestMiddleware(retryCount))
            .add(ApiResponseParseMiddleware<T>(json))
            .add(AppCodeProcessingMiddleware(appCodeProcessingPolicy))
    }
}
