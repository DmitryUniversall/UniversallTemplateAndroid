package com.universall.auth_impl.ui.screens.login_screen

import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.state.ResourceState

internal data class LoginScreenUIState(
    // Network
    val loginRequestState: ResourceState<Unit>,

    // Fields
    val loginFieldState: FieldState<String>,
    val passwordFieldState: FieldState<String>,

    // UI
    val isSentButtonEnabled: Boolean
) {
    companion object {
        fun empty(): LoginScreenUIState = LoginScreenUIState(
            loginRequestState = ResourceState.Idle,
            loginFieldState = FieldState(""),
            passwordFieldState = FieldState(""),
            isSentButtonEnabled = false
        )
    }
}
