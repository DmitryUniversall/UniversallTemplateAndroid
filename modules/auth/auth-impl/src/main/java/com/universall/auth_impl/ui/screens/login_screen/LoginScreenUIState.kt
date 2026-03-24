package com.universall.auth_impl.ui.screens.login_screen

import com.universall.appcore.ui.fields.state.generics.TextFieldState
import com.universall.appcore.ui.fields.state.validators.isOk
import com.universall.appcore.ui.resources.ResourceState
import com.universall.appcore.ui.resources.isFetching

internal data class LoginScreenUIState(
    // Network
    val loginRequestState: ResourceState<Unit>,

    // Fields
    val loginFieldState: TextFieldState,
    val passwordFieldState: TextFieldState,
) {
    companion object {
        fun empty(): LoginScreenUIState = LoginScreenUIState(
            loginRequestState = ResourceState.Idle,
            loginFieldState = TextFieldState(""),
            passwordFieldState = TextFieldState("")
        )
    }

    val isSendButtonEnabled: Boolean
        get() = loginFieldState.isOk() &&
                passwordFieldState.isOk() &&
                !loginRequestState.isFetching
}
