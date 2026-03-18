package com.universall.appcore.ui.navigation

import androidx.navigation.NamedNavArgument

interface AppDestination {
    val route: String
    val arguments: List<NamedNavArgument> get() = emptyList()
}
