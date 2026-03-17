package com.universall.appcore.network.impl.api.middleware

import com.universall.core.network.api.ApiRequestContext
import com.universall.core.network.api.ApiResponseContext
import com.universall.core.network.api.middleware.ApiClientMiddleware
import com.universall.core.policies.app_code.AppCodeProcessingPolicy
import io.ktor.client.request.HttpRequestBuilder

class AppCodeProcessingMiddleware(
    private val policy: AppCodeProcessingPolicy
) : ApiClientMiddleware<HttpRequestBuilder, ApiResponseContext<*>> {
    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): ApiResponseContext<*> {
        val response = next(input) as ApiResponseContext<*>
        policy.process(response.httpResponse.status, response.apiResponse.appCode, response.apiResponse.message)
        return response
    }
}
