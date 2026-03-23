package com.universall.main.ui.screens.main_screen

import com.universall.appcore.ui.navigation.AppDestination
import com.universall.main.ui.navigation.MainScreenPagerNavRoute

internal sealed interface MainScreenUIEffect {
    data class Navigate(
        val destination: AppDestination,
        val popUpTo: AppDestination? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : MainScreenUIEffect

    data class PagerNavigate(
        val destination: MainScreenPagerNavRoute
    ) : MainScreenUIEffect
}
