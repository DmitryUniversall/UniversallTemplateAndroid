package com.universall.appcore.appcore.network.api.interceptors.http_status_validation

import com.universall.appcore.base.network.api.attributes.ApiCallAttributeKey

object HttpStatusValidationApiCallFeature {
    // Request
    val Config = ApiCallAttributeKey<HttpStatusValidationConfig>("interceptor.http_status_validation.request.config")
}
