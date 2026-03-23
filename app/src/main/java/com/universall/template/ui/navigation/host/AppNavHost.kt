package com.universall.template.ui.navigation.host

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.universall.auth_impl.ui.screens.login_screen.LoginScreen
import com.universall.auth_impl.ui.screens.password_recovery_screen.PasswordRecoveryScreen
import com.universall.auth_impl.ui.screens.register_screen.RegisterScreen
import com.universall.init_impl.ui.screens.init_screen.InitScreen
import com.universall.main.ui.screens.main_screen.MainScreen
import com.universall.navigation_impl.destinations.auth.AuthDestination
import com.universall.navigation_impl.destinations.auth.LoginDestination
import com.universall.navigation_impl.destinations.auth.PasswordRecoveryDestination
import com.universall.navigation_impl.destinations.auth.RegisterDestination
import com.universall.navigation_impl.destinations.init.InitDestination
import com.universall.navigation_impl.destinations.main.MainScreenDestination

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

            composable(PasswordRecoveryDestination.route) {
                PasswordRecoveryScreen(innerPadding = innerPadding, navController = navController)
            }
        }

        composable(InitDestination.route) {
            InitScreen(innerPadding = innerPadding, navController = navController)
        }

        composable(MainScreenDestination.route) {
            MainScreen(innerPadding = innerPadding, navController = navController)
        }
    }
}
