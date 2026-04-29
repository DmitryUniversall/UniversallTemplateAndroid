package com.universall.appcore.appcore.navigation

import androidx.navigation.NamedNavArgument

interface AppDestination {
    val route: String
    val arguments: List<NamedNavArgument> get() = emptyList()
}
