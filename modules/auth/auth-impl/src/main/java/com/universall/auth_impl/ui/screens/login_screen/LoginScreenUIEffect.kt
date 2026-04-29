package com.universall.auth_impl.ui.screens.login_screen

import com.universall.appcore.appcore.navigation.AppDestination

internal sealed interface LoginScreenUIEffect {
    data class Navigate(
        val destination: AppDestination,
        val popUpTo: AppDestination? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : LoginScreenUIEffect
}
