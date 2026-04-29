package com.universall.appcore.appcore.network.api.interceptors.retry

import com.universall.appcore.base.network.api.ApiRequest

fun ApiRequest.Builder.enableRetrying(config: RetryConfig? = null) {
    attribute(RetryApiCallFeature.Config, config ?: RetryConfig())
}

fun ApiRequest.Builder.disableRetry() {
    removeAttribute(key = RetryApiCallFeature.Config)
}
