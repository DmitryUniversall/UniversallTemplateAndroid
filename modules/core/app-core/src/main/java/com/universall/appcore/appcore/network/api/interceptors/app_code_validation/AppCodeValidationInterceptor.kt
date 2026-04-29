package com.universall.appcore.appcore.network.api.interceptors.app_code_validation

import com.universall.appcore.appcore.network.api.interceptors.api_message_parse.ApiMessageParseApiCallFeature
import com.universall.appcore.base.network.api.ApiRequest
import com.universall.appcore.base.network.api.ApiResponse
import com.universall.appcore.base.network.api.AppCodeProcessingPolicy
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor

class AppCodeValidationInterceptor(
    private val appCodeProcessingPolicy: AppCodeProcessingPolicy
) : ApiCallInterceptor {
    override suspend fun process(
        input: ApiRequest,
        next: suspend (ApiRequest) -> ApiResponse
    ): ApiResponse {
        val response = next(input)

        val config = input.attributes.get(AppCodeValidationApiCallFeature.Config) ?: return response
        if (!config.enabled) return response

        val apiMessage = response.attributes.get(ApiMessageParseApiCallFeature.MessageDTO) ?: error("App code validation requires parsed api message attribute")

        appCodeProcessingPolicy.process(appStatusCode = apiMessage.appCode, request = input, response = response)

        return response
    }
}
