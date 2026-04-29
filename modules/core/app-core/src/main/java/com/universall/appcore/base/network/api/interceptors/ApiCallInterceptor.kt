package com.universall.appcore.base.network.api.interceptors

import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse

fun interface ApiCallInterceptor {
    suspend fun process(
        input: ApiRequest,
        next: suspend (ApiRequest) -> ApiResponse
    ): ApiResponse
}
