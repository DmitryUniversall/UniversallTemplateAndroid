package com.universall.appcore.base.network.api

interface ApiClient {
    suspend fun <T> request(
        transformer: ApiResponseTransformer<T>,
        configure: ApiRequest.Builder.() -> Unit
    ): T
}
