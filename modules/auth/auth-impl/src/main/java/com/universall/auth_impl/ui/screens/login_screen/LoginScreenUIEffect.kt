package com.universall.auth_impl.ui.screens.login_screen

import com.universall.appcore.ui.navigation.AppDestination

internal sealed interface LoginScreenUIEffect {
    data class Navigate(val destination: AppDestination) : LoginScreenUIEffect
}
