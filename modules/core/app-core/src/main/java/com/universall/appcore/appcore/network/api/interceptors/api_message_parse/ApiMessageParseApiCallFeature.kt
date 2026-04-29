package com.universall.appcore.appcore.network.api.interceptors.api_message_parse

import com.universall.appcore.appcore.dto.ApiMessageDTO
import com.universall.appcore.base.network.api.attributes.ApiCallAttributeKey

object ApiMessageParseApiCallFeature {
    // Request
    val Config = ApiCallAttributeKey<ApiMessageParseConfig>("interceptor.api_message_parse.request.config")

    // Response
    val MessageDTO = ApiCallAttributeKey<ApiMessageDTO>("interceptor.api_message_parse.response.message_dto")
}
