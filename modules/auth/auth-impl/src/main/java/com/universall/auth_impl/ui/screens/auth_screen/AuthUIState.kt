package com.universall.auth_impl.ui.screens.auth_screen

import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching

data class AuthUIState(
    val registerRequestState: ResourceState<Unit>,
    val registerInputInfo: RegisterInputInfo,
) {
    data class RegisterInputInfo(
        val login: FieldState<String> = FieldState(""),
        val password: FieldState<String> = FieldState(""),
        val firstName: FieldState<String> = FieldState(""),
        val lastName: FieldState<String> = FieldState(""),
        val username: FieldState<String> = FieldState(""),
    )

    companion object {
        fun empty(): AuthUIState = AuthUIState(
            registerRequestState = ResourceState.Idle,
            registerInputInfo = RegisterInputInfo(),
        )
    }
}
