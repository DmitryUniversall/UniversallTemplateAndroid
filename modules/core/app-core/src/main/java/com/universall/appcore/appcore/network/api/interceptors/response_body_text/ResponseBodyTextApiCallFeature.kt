package com.universall.appcore.appcore.network.api.interceptors.response_body_text

import com.universall.appcore.base.network.api.attributes.ApiCallAttributeKey

object ResponseBodyTextApiCallFeature {
    // Request
    val Config = ApiCallAttributeKey<ResponseBodyTextInterceptorConfig>("interceptor.body_text.request.config")

    // Response
    val BodyTextValue = ApiCallAttributeKey<String>("interceptor.body_text.response.body_text_value")
}
