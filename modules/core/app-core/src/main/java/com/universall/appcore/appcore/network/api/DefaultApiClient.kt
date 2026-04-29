package com.universall.appcore.appcore.network.api

import com.universall.appcore.appcore.network.api.interceptors.api_message_parse.ApiMessageParseInterceptor
import com.universall.appcore.appcore.network.api.interceptors.app_code_validation.AppCodeValidationInterceptor
import com.universall.appcore.appcore.network.api.interceptors.http_status_validation.HttpStatusValidationInterceptor
import com.universall.appcore.appcore.network.api.interceptors.response_body_text.ResponseBodyTextInterceptor
import com.universall.appcore.appcore.network.api.interceptors.retry.ApiCallRetryInterceptor
import com.universall.appcore.base.network.api.ApiCallTransport
import com.universall.appcore.base.network.api.ApiClient
import com.universall.appcore.base.network.api.AppCodeProcessingPolicy
import com.universall.appcore.base.network.api.CoreApiClient
import com.universall.appcore.base.network.api.interceptors.ApiCallInterceptor
import kotlinx.serialization.json.Json

internal class DefaultApiClient(
    transport: ApiCallTransport,
    interceptors: List<ApiCallInterceptor>,
    json: Json,
    appCodeProcessingPolicy: AppCodeProcessingPolicy
) : ApiClient by CoreApiClient(
    transport = transport,
    interceptors = listOf(
        ApiCallRetryInterceptor(),
        AppCodeValidationInterceptor(appCodeProcessingPolicy),
        ApiMessageParseInterceptor(json),
        HttpStatusValidationInterceptor(),
        ResponseBodyTextInterceptor()
    ) + interceptors
)
