package com.universall.auth_impl.ui.screens.password_recovery_screen

internal data class PasswordRecoveryScreenUIState(
    val forTemplate: Int = 0
) {
    companion object {
        fun empty(): PasswordRecoveryScreenUIState = PasswordRecoveryScreenUIState()
    }
}
