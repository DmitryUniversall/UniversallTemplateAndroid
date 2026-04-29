package com.universall.appcore.appcore.network.api.interceptors.retry

import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor
import com.universall.core.utils.retrying

class ApiCallRetryInterceptor : ApiCallInterceptor {
    override suspend fun process(
        input: ApiRequest,
        next: suspend (ApiRequest) -> ApiResponse
    ): ApiResponse {
        val config = input.attributes.get(RetryApiCallFeature.Config) ?: return next(input)
        return retrying(config.attempts) { next(input) }
    }
}
