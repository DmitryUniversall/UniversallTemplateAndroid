package com.universall.appcore.appcore.network.api.interceptors.app_code_validation

import com.universall.appcore.base.network.api.attributes.ApiCallAttributeKey

object AppCodeValidationApiCallFeature {
    // Request
    val Config = ApiCallAttributeKey<AppCodeValidationConfig>("interceptor.app_code_validation.request.config")
}
