package com.universall.appcore.appcore.network.api.interceptors.http_status_validation

import com.universall.appcore.base.network.api.ApiRequest

fun ApiRequest.Builder.validateHttpStatus(config: HttpStatusValidationConfig? = null) {
    attribute(HttpStatusValidationApiCallFeature.Config, config ?: HttpStatusValidationConfig())
}

fun ApiRequest.Builder.disableHttpStatusValidation() {
    removeAttribute(key = HttpStatusValidationApiCallFeature.Config)
}
