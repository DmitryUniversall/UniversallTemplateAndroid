package com.universall.appcore.base.network.api

fun interface ApiResponseTransformer<T> {
    suspend fun transform(response: ApiResponse): T
}
