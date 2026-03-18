package com.universall.template.ui.screens.init_screen

import com.universall.appcore.ui.navigation.AppDestination

sealed interface InitUIEffect {
    data class Navigate(val destination: AppDestination) : InitUIEffect
}
