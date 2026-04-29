package com.universall.appcore.base.network.api

interface AppCodeProcessingPolicy {
    fun process(appStatusCode: Int, request: ApiRequest, response: ApiResponse)
}
