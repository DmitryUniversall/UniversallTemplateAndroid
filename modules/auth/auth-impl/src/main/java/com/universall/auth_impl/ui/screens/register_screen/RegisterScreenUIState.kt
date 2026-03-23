package com.universall.auth_impl.ui.screens.register_screen

import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.state.ResourceState

internal data class RegisterScreenUIState(
    // Network
    val registerRequestState: ResourceState<Unit>,

    // Fields
    val loginFieldState: FieldState<String>,
    val passwordFieldState: FieldState<String>,
    val usernameFieldState: FieldState<String>,
    val firstNameFieldState: FieldState<String>,
    val lastNameFieldState: FieldState<String>,
) {
    companion object {
        fun empty(): RegisterScreenUIState = RegisterScreenUIState(
            registerRequestState = ResourceState.Idle,
            loginFieldState = FieldState(""),
            passwordFieldState = FieldState(""),
            usernameFieldState = FieldState(""),
            firstNameFieldState = FieldState(""),
            lastNameFieldState = FieldState("")
        )
    }
}
