package com.universall.auth_impl.ui.screens.register_screen

import com.universall.appcore.ui.fields.state.generics.TextFieldState
import com.universall.appcore.ui.fields.state.validators.isOk
import com.universall.appcore.ui.resources.ResourceState
import com.universall.appcore.ui.resources.isFetching

internal data class RegisterScreenUIState(
    // Network
    val registerRequestState: ResourceState<Unit>,

    // Fields
    val loginFieldState: TextFieldState,
    val passwordFieldState: TextFieldState,
    val usernameFieldState: TextFieldState,
    val firstNameFieldState: TextFieldState,
    val lastNameFieldState: TextFieldState,
) {
    companion object {
        fun empty(): RegisterScreenUIState = RegisterScreenUIState(
            registerRequestState = ResourceState.Idle,
            loginFieldState = TextFieldState(""),
            passwordFieldState = TextFieldState(""),
            usernameFieldState = TextFieldState(""),
            firstNameFieldState = TextFieldState(""),
            lastNameFieldState = TextFieldState("")
        )
    }

    val isSendButtonEnabled: Boolean
        get() = loginFieldState.isOk() &&
                passwordFieldState.isOk() &&
                usernameFieldState.isOk() &&
                firstNameFieldState.isOk() &&
                lastNameFieldState.isOk() &&
                !registerRequestState.isFetching
}
