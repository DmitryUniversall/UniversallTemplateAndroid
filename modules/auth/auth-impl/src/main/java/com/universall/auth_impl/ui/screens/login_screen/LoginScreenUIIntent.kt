package com.universall.auth_impl.ui.screens.login_screen

import com.universall.appcore.ui.fields.FieldState

internal sealed interface LoginScreenUIIntent {
    object NavigateToRegister : LoginScreenUIIntent

    data class SendLogin(val login: String, val password: String) : LoginScreenUIIntent

    sealed interface Input : LoginScreenUIIntent {
        data class InputLogin(val value: String) : Input
        data class InputPassword(val value: String) : Input
    }

    sealed interface Validate : LoginScreenUIIntent {
        data class ValidateLoginField(val fieldState: FieldState<String>) : Validate  // TODO: Take field state from UI state?
        data class ValidatePasswordField(val fieldState: FieldState<String>) : Validate
    }
}
