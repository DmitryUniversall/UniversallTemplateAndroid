package com.universall.auth_impl.ui.screens.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.ui.add
import com.universall.appcore.ui.clearFocusOnTap
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.ui.screens.login_screen.components.selections.GreetingsSelection
import com.universall.auth_impl.ui.screens.login_screen.components.selections.LoginFormSelection
import com.universall.auth_impl.ui.screens.login_screen.components.selections.RegisterSuggestionSelection

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    LoginScreen(
        viewModel = hiltViewModel(),
        innerPadding = innerPadding,
        navController = navController
    )
}

@Composable
internal fun LoginScreen(
    viewModel: LoginScreenViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    val spacing = Locals.spacing

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is LoginScreenUIEffect.Navigate -> navController.navigate(effect.destination.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding.add(spacing.screenPadding))
            .clearFocusOnTap(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        GreetingsSelection()

        LoginFormSelection(
            onIntent = viewModel::onIntent,
            loginFieldState = uiState.loginFieldState,
            passwordFieldState = uiState.passwordFieldState,
            loginRequestState = uiState.loginRequestState,
            isSendButtonEnabled = uiState.isSentButtonEnabled
        )

        RegisterSuggestionSelection()
    }
}
