package com.universall.appcore.network.api.middleware.generics

import com.universall.appcore.network.api.ApiRequestContext
import com.universall.appcore.network.api.middleware.ApiClientMiddleware
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

class KtorExecuteMiddleware(
    private val client: HttpClient
) : ApiClientMiddleware<HttpRequestBuilder, HttpResponse> {
    override suspend fun proceed(
        context: ApiRequestContext,
        next: suspend (HttpRequestBuilder) -> Any?,
        input: HttpRequestBuilder
    ): HttpResponse {
        return client.request(input)
    }
}
