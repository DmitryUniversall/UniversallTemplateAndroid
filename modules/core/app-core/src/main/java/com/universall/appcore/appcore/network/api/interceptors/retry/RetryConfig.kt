package com.universall.appcore.appcore.network.api.interceptors.retry

data class RetryConfig(
    val attempts: Int = 3,
    val initialDelayMs: Long = 100,
    val backoffFactor: Double = 2.0
)
