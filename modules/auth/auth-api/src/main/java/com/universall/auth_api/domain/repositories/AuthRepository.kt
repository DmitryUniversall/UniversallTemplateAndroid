package com.universall.auth_api.domain.repositories

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.entities.LocalAuthInfo
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.schemas.RegisterSchema
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authStateFlow: StateFlow<AuthState>

    suspend fun setAuthState(state: AuthState, syncLocal: Boolean = true)

    suspend fun getLocalAuthInfo(): LocalAuthInfo?
    suspend fun setLocalAuthInfo(localAuthInfo: LocalAuthInfo)
    suspend fun clearLocalAuthInfo()

    suspend fun getCurrentAuthContext(accessToken: String): Result<AuthContext>
    suspend fun register(schema: RegisterSchema): Result<Pair<AuthContext, AuthTokenPair>>
    suspend fun login(schema: LoginSchema): Result<Pair<AuthContext, AuthTokenPair>>
    suspend fun refresh(refreshToken: String): Result<AuthTokenPair>
}
