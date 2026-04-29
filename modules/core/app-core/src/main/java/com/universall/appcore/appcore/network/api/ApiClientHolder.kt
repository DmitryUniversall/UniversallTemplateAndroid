package com.universall.appcore.appcore.network.api

import com.universall.appcore.base.network.api.ApiCallTransport
import com.universall.appcore.base.network.api.AppCodeProcessingPolicy
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor
import kotlinx.serialization.json.Json

internal object ApiClientHolder {
    fun buildApiClient(
        transport: ApiCallTransport,
        interceptors: List<ApiCallInterceptor>,
        json: Json,
        appCodeProcessingPolicy: AppCodeProcessingPolicy
    ): DefaultApiClient {
        return DefaultApiClient(
            transport = transport,
            interceptors = interceptors,
            json = json,
            appCodeProcessingPolicy = appCodeProcessingPolicy,
        )
    }
}
