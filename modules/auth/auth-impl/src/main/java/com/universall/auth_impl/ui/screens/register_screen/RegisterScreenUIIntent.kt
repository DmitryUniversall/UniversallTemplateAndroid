package com.universall.auth_impl.ui.screens.register_screen

internal sealed interface RegisterScreenUIIntent {
    object NavigateToLogin : RegisterScreenUIIntent

    object SendRegister : RegisterScreenUIIntent

    sealed interface Input : RegisterScreenUIIntent {
        data class InputLogin(val value: String) : Input
        data class InputPassword(val value: String) : Input
        data class InputUsername(val value: String) : Input
        data class InputFirstName(val value: String) : Input
        data class InputLastName(val value: String) : Input
    }

    sealed interface Validate : RegisterScreenUIIntent {
        object ValidateLoginField : Validate
        object ValidatePasswordField : Validate
        object ValidateUsernameField : Validate
        object ValidateFirstNameField : Validate
        object ValidateLastNameField : Validate
    }
}
