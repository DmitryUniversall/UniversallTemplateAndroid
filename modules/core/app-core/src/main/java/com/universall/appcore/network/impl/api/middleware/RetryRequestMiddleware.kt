package com.universall.appcore.network.impl.api.middleware

import com.universall.appcore.network.impl.api.base.ApiRequestContext
import com.universall.appcore.network.impl.api.base.middleware.ApiClientMiddleware
import com.universall.core.utils.retrying
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

class RetryRequestMiddleware(
    val retryCount: Int
) : ApiClientMiddleware<HttpRequestBuilder, HttpResponse> {
    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): HttpResponse {
        return retrying(
            attempts = retryCount,
            initialDelayMs = 200,
            backoffFactor = 2.0
        ) {
            next(input) as HttpResponse
        }
    }
}
