package com.universall.appcore.base.network.api

fun interface ApiCallTransport {
    suspend fun execute(request: ApiRequest): ApiResponse
}
