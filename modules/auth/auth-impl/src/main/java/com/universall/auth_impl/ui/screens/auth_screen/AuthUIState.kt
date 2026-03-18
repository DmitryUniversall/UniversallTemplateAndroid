package com.universall.auth_impl.ui.screens.auth_screen

import com.universall.appcore.ui.state.ResourceState

data class AuthUIState(
    val loginRequestState: ResourceState<Unit>,
    val loginInputInfo: LoginInputInfo,
    val registerRequestState: ResourceState<Unit>,
    val registerInputInfo: RegisterInputInfo,
    val authMode: AuthMode
) {
    data class LoginInputInfo(
        val login: String = "",
        val password: String = ""
    )

    data class RegisterInputInfo(
        val login: String = "",
        val password: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val username: String = ""
    )

    companion object {
        fun empty(): AuthUIState = AuthUIState(
            loginRequestState = ResourceState.Idle,
            loginInputInfo = LoginInputInfo(),
            registerRequestState = ResourceState.Idle,
            registerInputInfo = RegisterInputInfo(),
            authMode = AuthMode.LOGIN
        )
    }
}
