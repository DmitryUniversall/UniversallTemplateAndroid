package com.universall.server_tools_impl.data.sources

import com.universall.appcore.network.api.ApiClient
import com.universall.appcore.network.api.base.ApiRequestContext
import io.ktor.client.plugins.timeout
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ServerToolsNetworkDataSource @Inject constructor(
    private val apiClient: ApiClient
) {
    private val serverToolsFeaturePathSegment = "tools"

    private val pingPathSegment = "ping"

    suspend fun ping(): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            apiClient.requestUnit(
                contextBuilder = ApiRequestContext.Builder().meta("retryCount", 1)
            ) {
                method = HttpMethod.Get
                url { appendPathSegments(serverToolsFeaturePathSegment, pingPathSegment) }

                timeout {
                    requestTimeoutMillis = 5_000
                    connectTimeoutMillis = 5_000
                    socketTimeoutMillis = 5_000
                }
            }

            Unit
        }
    }
}
