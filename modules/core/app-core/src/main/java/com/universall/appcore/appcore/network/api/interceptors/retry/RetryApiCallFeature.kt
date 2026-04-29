package com.universall.appcore.appcore.network.api.interceptors.retry

import com.universall.appcore.base.network.api.attributes.ApiCallAttributeKey

object RetryApiCallFeature {
    // Request
    val Config = ApiCallAttributeKey<RetryConfig>("interceptor.retry.request.config")
}
