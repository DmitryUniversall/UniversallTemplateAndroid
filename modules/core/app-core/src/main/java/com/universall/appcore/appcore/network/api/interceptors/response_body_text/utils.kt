package com.universall.appcore.appcore.network.api.interceptors.response_body_text

import com.universall.appcore.base.network.api.ApiRequest

fun ApiRequest.Builder.requireBodyText(config: ResponseBodyTextInterceptorConfig? = null) {
    attribute(ResponseBodyTextApiCallFeature.Config, config ?: ResponseBodyTextInterceptorConfig())
}

fun ApiRequest.Builder.disableBodyTextParing() {
    removeAttribute(key = ResponseBodyTextApiCallFeature.Config)
}
