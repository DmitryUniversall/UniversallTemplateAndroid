package com.universall.appcore.network.api.middleware.generics

import com.universall.appcore.network.api.ApiRequestContext
import com.universall.appcore.network.api.ApiResponseContext
import com.universall.appcore.network.api.middleware.ApiClientMiddleware
import com.universall.appcore.policies.app_code.AppCodeProcessingPolicy
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
