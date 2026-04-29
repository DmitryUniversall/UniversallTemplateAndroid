package com.universall.appcore.appcore.network.ktor

import com.universall.appcore.base.network.api.ApiCallTransport
import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import jakarta.inject.Inject

internal class KtorApiCallTransport @Inject constructor(
    val httpClient: HttpClient
) : ApiCallTransport {
    override suspend fun execute(request: ApiRequest): ApiResponse {
        val response = httpClient.request {
            this.apply(block = request.buildRequest)
        }

        return ApiResponse(httpResponse = response)
    }
}
