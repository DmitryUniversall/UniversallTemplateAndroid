package com.universall.appcore.appcore.network.api.interceptors.http_status_validation

import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor
import com.universall.appcore.base.network.exceptions.ApiCallException
import io.ktor.http.isSuccess

class HttpStatusValidationInterceptor : ApiCallInterceptor {
    override suspend fun process(
        input: ApiRequest,
        next: suspend (ApiRequest) -> ApiResponse
    ): ApiResponse {
        val response = next(input)
        val config = input.attributes.get(HttpStatusValidationApiCallFeature.Config) ?: return response

        if (!config.enabled) return response

        if (!response.httpResponse.status.isSuccess()) {
            throw ApiCallException(
                message = null,
                request = input,
                response = response,
            )
        }

        return response
    }
}
