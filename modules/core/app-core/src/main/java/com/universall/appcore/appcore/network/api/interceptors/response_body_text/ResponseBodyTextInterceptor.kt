package com.universall.appcore.appcore.network.api.interceptors.response_body_text

import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor
import io.ktor.client.statement.bodyAsText

class ResponseBodyTextInterceptor : ApiCallInterceptor {
    override suspend fun process(
        input: ApiRequest,
        next: suspend (ApiRequest) -> ApiResponse
    ): ApiResponse {
        val response = next(input)

        val config = input.attributes.get(ResponseBodyTextApiCallFeature.Config) ?: return response
        if (!config.enabled) return response

        val bodyText = response.httpResponse.bodyAsText()
        response.attributes.put(ResponseBodyTextApiCallFeature.BodyTextValue, bodyText)

        return response
    }
}
