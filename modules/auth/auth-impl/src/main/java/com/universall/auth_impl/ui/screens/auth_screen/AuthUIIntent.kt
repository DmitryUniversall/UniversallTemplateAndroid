package com.universall.auth_impl.ui.screens.auth_screen

import com.universall.appcore.ui.fields.FieldState

sealed interface AuthUIIntent {
    data class SendRegister(val input: AuthUIState.RegisterInputInfo) : AuthUIIntent

    data class InputRegister(val input: AuthUIState.RegisterInputInfo) : AuthUIIntent

    sealed interface Validate : AuthUIIntent {
        sealed interface Register : Validate {
            data class LoginField(val fieldState: FieldState<String>) : Register
            data class PasswordField(val fieldState: FieldState<String>) : Register
            data class FirstNameField(val fieldState: FieldState<String>) : Register
            data class LastNameField(val fieldState: FieldState<String>) : Register
            data class UsernameField(val fieldState: FieldState<String>) : Register
        }
    }
}
