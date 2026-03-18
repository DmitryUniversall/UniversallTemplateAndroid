package com.universall.template.ui.screens.init_screen

sealed interface InitIntent {
    object RestoreAuthState : InitIntent
    object Refresh : InitIntent
    object LocalLogout : InitIntent
}
