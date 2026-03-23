package com.universall.auth_impl.data.repositories

import com.universall.auth_api.domain.entities.AuthSession
import com.universall.auth_api.domain.repositories.SessionsRepository
import com.universall.auth_impl.data.sources.SessionsNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionsRepositoryImpl @Inject constructor(
    private val networkDataSource: SessionsNetworkDataSource
) : SessionsRepository {
    override suspend fun getActiveSessions(): Result<List<AuthSession>> {
        return networkDataSource.getActiveSessions().map { dto -> dto.sessions.map { it.toEntity() } }
    }

    override suspend fun revokeCurrentSession(): Result<Unit> {
        return networkDataSource.logout()
    }

    override suspend fun revokeAllSessionsExceptCurrent(): Result<Unit> {
        return networkDataSource.revokeAllSessionsExceptCurrent()
    }

    override suspend fun revokeSession(sessionUUID: String): Result<Unit> {
        return networkDataSource.revokeSession(sessionUUID)
    }
}
