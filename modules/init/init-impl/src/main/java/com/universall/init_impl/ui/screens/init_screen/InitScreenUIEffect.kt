package com.universall.init_impl.ui.screens.init_screen

import com.universall.appcore.appcore.navigation.AppDestination

internal sealed interface InitScreenUIEffect {
    data class Navigate(
        val destination: AppDestination,
        val popUpTo: AppDestination? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : InitScreenUIEffect
}
