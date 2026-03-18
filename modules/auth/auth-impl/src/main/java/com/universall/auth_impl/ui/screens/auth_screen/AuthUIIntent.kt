package com.universall.auth_impl.ui.screens.auth_screen

import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.schemas.RegisterSchema

sealed interface AuthUIIntent {
    data class SendLogin(val schema: LoginSchema) : AuthUIIntent
    data class SendRegister(val schema: RegisterSchema) : AuthUIIntent

    data class InputLogin(val input: AuthUIState.LoginInputInfo) : AuthUIIntent
    data class InputRegister(val input: AuthUIState.RegisterInputInfo) : AuthUIIntent

    data class SetAuthMode(val authMode: AuthMode) : AuthUIIntent
}
