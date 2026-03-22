package com.universall.auth_impl.ui.screens.auth_screen

import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching

data class AuthUIState(
    val loginRequestState: ResourceState<Unit>,
    val loginInputInfo: LoginInputInfo,
    val registerRequestState: ResourceState<Unit>,
    val registerInputInfo: RegisterInputInfo,
    val authMode: AuthMode,
    val isInputEnabled: Boolean = true,
) {
    data class LoginInputInfo(
        val login: FieldState<String> = FieldState(""),
        val password: FieldState<String> = FieldState(""),
        val isLoginButtonEnabled: Boolean = false
    )

    data class RegisterInputInfo(
        val login: FieldState<String> = FieldState(""),
        val password: FieldState<String> = FieldState(""),
        val firstName: FieldState<String> = FieldState(""),
        val lastName: FieldState<String> = FieldState(""),
        val username: FieldState<String> = FieldState(""),
        val isRegisterButtonEnabled: Boolean = false
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

    val isFetchingAnything: Boolean get() = loginRequestState.isFetching || registerRequestState.isFetching
    val isSendButtonEnabled: Boolean get() = if (authMode == AuthMode.LOGIN) loginInputInfo.isLoginButtonEnabled else registerInputInfo.isRegisterButtonEnabled
}
