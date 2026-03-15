package com.universall.core.utils

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("CureUtils")

data class RetryScope(val currentAttempt: Int)

suspend fun <T> retrying(
    attempts: Int,
    initialDelayMs: Long = 100,
    backoffFactor: Double = 2.0,
    shouldRetry: (Exception) -> Boolean = { true },
    block: suspend RetryScope.() -> T
): T {
    require(attempts >= 1) { "attempts must be >= 1" }

    var delay = initialDelayMs
    var lastException: Exception? = null

    repeat(attempts) { idx ->
        val scope = RetryScope(currentAttempt = idx)

        try {
            return block.invoke(scope)
        } catch (e: Exception) {
            lastException = e
            if (!shouldRetry(e) || idx == attempts - 1) throw e

            logger.error("Attempt ${idx + 1}/$attempts failed: ${e::class.simpleName}: ${e.message}")

            delay(delay)
            delay = (delay * backoffFactor).toLong()
        }
    }

    throw lastException ?: IllegalStateException("retrying ended unexpectedly")
}
