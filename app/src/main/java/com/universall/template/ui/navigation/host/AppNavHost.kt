package com.universall.template.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.universall.auth_api.ui.navigation.AuthDestination
import com.universall.auth_api.ui.navigation.LoginDestination
import com.universall.auth_api.ui.navigation.RegisterDestination
import com.universall.auth_impl.ui.screens.login_screen.LoginScreen
import com.universall.auth_impl.ui.screens.register_screen.RegisterScreen
import com.universall.init_api.ui.navigation.InitDestination
import com.universall.init_impl.ui.screens.init_screen.InitScreen

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

        navigation(
            route = AuthDestination.route,
            startDestination = LoginDestination.route
        ) {
            composable(LoginDestination.route) {
                LoginScreen(innerPadding = innerPadding, navController = navController)
            }

            composable(RegisterDestination.route) {
                RegisterScreen(innerPadding = innerPadding, navController = navController)
            }
        }
    }
}
