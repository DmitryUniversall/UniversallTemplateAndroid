package com.universall.auth_impl.ui.screens.login_screen

import com.universall.appcore.appcore.ui.fields.state.TextFieldState
import com.universall.appcore.base.ui.fields.isOk
import com.universall.appcore.appcore.ui.state.RequestState
import com.universall.appcore.appcore.ui.state.isFetching

internal data class LoginScreenUIState(
    // Network
    val loginRequestState: RequestState<Unit>,

    // Fields
    val loginFieldState: TextFieldState,
    val passwordFieldState: TextFieldState,
) {
    companion object {
        fun empty(): LoginScreenUIState = LoginScreenUIState(
            loginRequestState = RequestState.Idle,
            loginFieldState = TextFieldState(""),
            passwordFieldState = TextFieldState("")
        )
    }

    val isSendButtonEnabled: Boolean
        get() = loginFieldState.isOk() &&
                passwordFieldState.isOk() &&
                !loginRequestState.isFetching
}
