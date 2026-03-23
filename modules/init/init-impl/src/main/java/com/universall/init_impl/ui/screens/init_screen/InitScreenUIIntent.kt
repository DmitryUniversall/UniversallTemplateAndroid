package com.universall.init_impl.ui.screens.init_screen

internal sealed interface InitScreenUIIntent {
    object RestoreAuthState : InitScreenUIIntent
    object Refresh : InitScreenUIIntent
    object LocalLogout : InitScreenUIIntent
}
