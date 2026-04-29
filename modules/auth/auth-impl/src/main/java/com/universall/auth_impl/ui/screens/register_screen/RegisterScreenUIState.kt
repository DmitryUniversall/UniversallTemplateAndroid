package com.universall.auth_impl.ui.screens.register_screen

import com.universall.appcore.appcore.ui.fields.state.TextFieldState
import com.universall.appcore.base.ui.fields.isOk
import com.universall.appcore.appcore.ui.state.RequestState
import com.universall.appcore.appcore.ui.state.isFetching

internal data class RegisterScreenUIState(
    // Network
    val registerRequestState: RequestState<Unit>,

    // Fields
    val loginFieldState: TextFieldState,
    val passwordFieldState: TextFieldState,
    val usernameFieldState: TextFieldState,
    val firstNameFieldState: TextFieldState,
    val lastNameFieldState: TextFieldState,
) {
    companion object {
        fun empty(): RegisterScreenUIState = RegisterScreenUIState(
            registerRequestState = RequestState.Idle,
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
