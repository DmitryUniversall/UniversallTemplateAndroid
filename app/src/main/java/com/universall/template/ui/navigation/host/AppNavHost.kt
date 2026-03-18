package com.universall.template.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.universall.auth_impl.ui.navigation.AuthDestination
import com.universall.auth_impl.ui.screens.auth_screen.AuthScreen
import com.universall.template.ui.navigation.destinations.InitDestination
import com.universall.template.ui.screens.init_screen.InitScreen

@Composable
fun AppNavHost(
    innerPadding: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = InitDestination.route
    ) {
        composable(InitDestination.route) {
            InitScreen(innerPadding = innerPadding, navController = navController)
        }

        composable(AuthDestination.route) {
            AuthScreen(innerPadding = innerPadding, navController = navController)
        }
    }
}
