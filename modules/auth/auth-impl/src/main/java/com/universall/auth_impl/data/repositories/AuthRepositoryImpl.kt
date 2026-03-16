package com.universall.auth_impl.data.repositories

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.entities.LocalAuthInfo
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_impl.data.sources.AuthLocalDataSource
import com.universall.auth_impl.data.sources.AuthNetworkDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepositoryImpl(
    private val localDataSource: AuthLocalDataSource,
    private val networkDataSource: AuthNetworkDataSource
) : AuthRepository {
    private val _authStateFlow = MutableStateFlow<AuthState>(AuthState.Unknown)
    override val authStateFlow: StateFlow<AuthState> = _authStateFlow.asStateFlow()

    override suspend fun setAuthState(state: AuthState, syncLocal: Boolean) {
        if (syncLocal) {
            when (state) {
                is AuthState.Unknown, is AuthState.TemporarilyUnauthenticated -> {}
                is AuthState.Unauthenticated -> localDataSource.clearAuthInfo()
                is AuthState.Authenticated -> {
                    localDataSource.saveAuthInfo(
                        LocalAuthInfo(
                            accessToken = state.tokenPair.accessToken,
                            refreshToken = state.tokenPair.refreshToken
                        )
                    )
                }
            }
        }

        _authStateFlow.value = state
    }

    override suspend fun getLocalAuthInfo(): LocalAuthInfo? = localDataSource.getAuthInfo()

    override suspend fun getCurrentAuthContext(accessToken: String): Result<AuthContext> {
        return networkDataSource.getCurrentAuthContext(accessToken).map { it.context.toEntity() }
    }

    override suspend fun register(shema: RegisterSchema): Result<AuthState.Authenticated> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Result<AuthState.Authenticated> {
        TODO("Not yet implemented")
    }

    override suspend fun refresh(refreshToken: String): Result<AuthTokenPair> {
        TODO("Not yet implemented")
    }
}
