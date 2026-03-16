package com.universall.auth_api.domain.entities

sealed interface AuthState {
    object Unknown : AuthState
    object Unauthenticated : AuthState
    data class TemporarilyUnauthenticated(val reason: String) : AuthState
    data class Authenticated(val context: AuthContext, val tokenPair: AuthTokenPair) : AuthState
}
