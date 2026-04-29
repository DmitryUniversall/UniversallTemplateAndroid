package com.universall.appcore.appcore.network.api.interceptors.app_code_validation

import com.universall.appcore.appcore.network.api.interceptors.api_message_parse.expectApiMessage
import com.universall.appcore.base.network.api.ApiRequest

fun ApiRequest.Builder.validateAppCode(config: AppCodeValidationConfig? = null) {
    expectApiMessage()
    attribute(AppCodeValidationApiCallFeature.Config, config ?: AppCodeValidationConfig())
}

fun ApiRequest.Builder.disableAppCodeValidation() {
    removeAttribute(key = AppCodeValidationApiCallFeature.Config)
}
