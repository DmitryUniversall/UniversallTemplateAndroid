package com.universall.auth_impl.ui.screens.login_screen

internal sealed interface LoginScreenUIIntent {
    object NavigateToPasswordRecovery : LoginScreenUIIntent
    object NavigateToRegister : LoginScreenUIIntent

    object SendLogin : LoginScreenUIIntent

    sealed interface Input : LoginScreenUIIntent {
        data class InputLogin(val value: String) : Input
        data class InputPassword(val value: String) : Input
    }

    sealed interface Validate : LoginScreenUIIntent {
        object ValidateLoginField : Validate
        object ValidatePasswordField : Validate
    }
}
