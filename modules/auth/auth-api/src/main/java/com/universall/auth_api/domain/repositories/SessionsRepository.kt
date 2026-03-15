package com.universall.auth_api.domain.repositories

import com.universall.auth_api.domain.entities.AuthSession

interface SessionsRepository {
    suspend fun getActiveSessions(): Result<List<AuthSession>>
    suspend fun revokeCurrentSession(): Result<Unit>
    suspend fun revokeAllSessionsExceptCurrent(): Result<Unit>
    suspend fun revokeSession(sessionUUID: String): Result<Unit>
}
