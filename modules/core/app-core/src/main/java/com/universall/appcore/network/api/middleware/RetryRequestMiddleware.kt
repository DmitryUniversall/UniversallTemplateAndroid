package com.universall.appcore.network.api.middleware

import com.universall.appcore.BuildConfig
import com.universall.appcore.network.api.base.ApiRequestContext
import com.universall.appcore.network.impl.api.base.middleware.ApiClientMiddleware
import com.universall.core.utils.retrying
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

class RetryRequestMiddleware : ApiClientMiddleware<HttpRequestBuilder, HttpResponse> {
    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): HttpResponse {
        val retryCount = context.getFromMeta<Int>("retryCount") ?: BuildConfig.NETWORK_REQUEST_RETRY_COUNT

        return retrying(
            attempts = retryCount + 1,  // 1 request; N retries
            initialDelayMs = 200,
            backoffFactor = 2.0
        ) {
            next(input) as HttpResponse
        }
    }
}
