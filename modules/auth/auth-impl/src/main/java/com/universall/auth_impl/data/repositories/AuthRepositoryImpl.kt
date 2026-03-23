package com.universall.auth_impl.data.repositories

import com.universall.auth_api.domain.entities.AuthContext
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.entities.AuthTokenPair
import com.universall.auth_api.domain.entities.LocalAuthInfo
import com.universall.auth_api.domain.repositories.AuthRepository
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_impl.data.sources.AuthLocalDataSource
import com.universall.auth_impl.data.sources.AuthNetworkDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val networkDataSource: AuthNetworkDataSource
) : AuthRepository {
    private val _authStateFlow = MutableStateFlow<AuthState>(AuthState.Unknown)
    override val authStateFlow: StateFlow<AuthState> = _authStateFlow.asStateFlow()

    override suspend fun setAuthState(state: AuthState, syncLocal: Boolean) {
        if (syncLocal) {
            when (state) {
                is AuthState.Unknown, is AuthState.TemporarilyUnauthenticated -> {}
                is AuthState.Unauthenticated -> clearLocalAuthInfo()
                is AuthState.Authenticated -> setLocalAuthInfo(
                    LocalAuthInfo(
                        accessToken = state.tokenPair.accessToken,
                        refreshToken = state.tokenPair.refreshToken
                    )
                )
            }
        }

        _authStateFlow.value = state
    }

    override suspend fun getLocalAuthInfo(): LocalAuthInfo? = localDataSource.getAuthInfo()
    override suspend fun setLocalAuthInfo(localAuthInfo: LocalAuthInfo) = localDataSource.saveAuthInfo(localAuthInfo)
    override suspend fun clearLocalAuthInfo() = localDataSource.clearAuthInfo()

    override suspend fun getCurrentAuthContext(accessToken: String): Result<AuthContext> {
        return networkDataSource.getCurrentAuthContext(accessToken).map { dto -> dto.context.toEntity() }
    }

    override suspend fun register(schema: RegisterSchema): Result<Pair<AuthContext, AuthTokenPair>> {
        return networkDataSource.register(schema).map { dto ->
            AuthContext(  // TODO: Refactor server
                session = dto.session.toEntity(),
                user = dto.user.toEntity(),
                accessInfo = dto.accessInfo.toEntity()
            ) to AuthTokenPair(
                accessToken = dto.tokens.accessToken,
                refreshToken = dto.tokens.refreshToken
            )
        }
    }

    override suspend fun login(schema: LoginSchema): Result<Pair<AuthContext, AuthTokenPair>> {
        return networkDataSource.login(schema).map { dto ->
            AuthContext(
                session = dto.session.toEntity(),
                user = dto.user.toEntity(),
                accessInfo = dto.accessInfo.toEntity()
            ) to AuthTokenPair(
                accessToken = dto.tokens.accessToken,
                refreshToken = dto.tokens.refreshToken
            )
        }
    }

    override suspend fun refresh(refreshToken: String): Result<AuthTokenPair> {
        return networkDataSource.refresh(refreshToken).map { dto ->
            AuthTokenPair(
                accessToken = dto.accessToken,
                refreshToken = dto.refreshToken
            )
        }
    }
}
