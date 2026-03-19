package com.universall.auth_impl.data.sources

import com.universall.appcore.network.impl.api.base.CoreApiClient
import com.universall.auth_api.domain.di.qualifiers.AuthenticatedApiClient
import com.universall.auth_impl.data.dto.get_active_sessions.GetActiveSessionsResponseDTO
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionsNetworkDataSource @Inject constructor(
    @param:AuthenticatedApiClient private val authenticatedApiClient: CoreApiClient
) {
    private val authFeaturePath = "/auth"

    private val logoutPath = "$authFeaturePath/logout"
    private val getActiveSessionsPath = "$authFeaturePath/sessions/active"
    private val revokeSessionPath = "$authFeaturePath/sessions/%d"
    private val revokeAllSessionsExceptCurrentPath = "$authFeaturePath/sessions/active"

    suspend fun getActiveSessions(): Result<GetActiveSessionsResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            authenticatedApiClient.requestDataNotNull<GetActiveSessionsResponseDTO> {
                method = HttpMethod.Get
                url { path(getActiveSessionsPath) }
            }
        }
    }

    suspend fun logout(): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            authenticatedApiClient.request<Unit> {
                method = HttpMethod.Delete
                url { path(logoutPath) }
            }

            return@runCatching
        }
    }

    suspend fun revokeSession(sessionUuid: String): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            authenticatedApiClient.request<Unit> {
                method = HttpMethod.Delete
                url { path(revokeSessionPath.format(sessionUuid)) }
            }

            return@runCatching
        }
    }

    suspend fun revokeAllSessionsExceptCurrent(): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            authenticatedApiClient.request<Unit> {
                method = HttpMethod.Delete
                url { path(revokeAllSessionsExceptCurrentPath) }
            }

            return@runCatching
        }
    }
}
