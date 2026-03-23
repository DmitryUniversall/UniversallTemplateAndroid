package com.universall.main.ui.screens.main_screen

internal sealed interface MainScreenUIIntent {
    object NavigateToHome : MainScreenUIIntent
    object NavigateToProfile : MainScreenUIIntent
}
