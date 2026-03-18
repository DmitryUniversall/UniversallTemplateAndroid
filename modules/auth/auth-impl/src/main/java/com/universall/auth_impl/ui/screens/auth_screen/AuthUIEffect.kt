package com.universall.auth_impl.ui.screens.auth_screen

import com.universall.appcore.ui.navigation.AppDestination

sealed interface AuthUIEffect {
    data class Navigate(val destination: AppDestination) : AuthUIEffect
}
