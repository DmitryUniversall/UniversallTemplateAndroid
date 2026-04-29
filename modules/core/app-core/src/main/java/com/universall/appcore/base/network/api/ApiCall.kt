package com.universall.appcore.base.network.api

import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor

class ApiCall(
    private val transport: ApiCallTransport,
    private val interceptors: List<ApiCallInterceptor>
) {
    suspend fun execute(request: ApiRequest): ApiResponse {
        val chain = interceptors.foldRight(
            initial = suspend { req: ApiRequest -> transport.execute(request = req) }
        ) { interceptor, acc ->
            { currentRequest -> interceptor.process(input = currentRequest, next = acc) }
        }

        return chain(request)
    }
}
