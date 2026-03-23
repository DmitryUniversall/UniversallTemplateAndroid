package com.universall.auth_impl.ui.screens.register_screen

import com.universall.appcore.ui.navigation.AppDestination

internal sealed interface RegisterScreenUIEffect {
    data class Navigate(
        val destination: AppDestination,
        val popUpTo: AppDestination? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : RegisterScreenUIEffect
}
