package com.universall.appcore.appcore.network.api.interceptors.api_message_parse

import com.universall.appcore.appcore.network.api.interceptors.response_body_text.requireBodyText
import com.universall.appcore.base.network.api.ApiRequest

fun ApiRequest.Builder.expectApiMessage(config: ApiMessageParseConfig? = null) {
    requireBodyText()
    attribute(ApiMessageParseApiCallFeature.Config, config ?: ApiMessageParseConfig())
}

fun ApiRequest.Builder.disableApiMessageParsing() {
    removeAttribute(key = ApiMessageParseApiCallFeature.Config)
}
