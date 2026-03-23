package com.universall.auth_impl.ui.screens.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.ui.add
import com.universall.appcore.ui.clearFocusOnTap
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.login_screen.components.selections.LoginErrorSelection
import com.universall.auth_impl.ui.screens.login_screen.components.selections.LoginForgotPasswordSelection
import com.universall.auth_impl.ui.screens.login_screen.components.selections.LoginFormSelection
import com.universall.auth_impl.ui.screens.login_screen.components.selections.LoginSendButtonSelection
import com.universall.auth_impl.ui.screens.login_screen.components.selections.RegisterSuggestionSelection
import com.universall.auth_impl.ui.shared.components.selections.GreetingsSelection

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
                is LoginScreenUIEffect.Navigate -> navController.navigate(effect.destination.route) {
                    effect.popUpTo?.let { popUpTo(it.route) { inclusive = effect.inclusive } }
                    launchSingleTop = effect.singleTop
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding.add(spacing.screenPadding))
            .clearFocusOnTap()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            GreetingsSelection(
                titleText = stringResource(R.string.welcome_back),
                actionText = stringResource(R.string.log_in_to_continue)
            )

            LoginFormSelection(
                onIntent = viewModel::onIntent,
                loginRequestState = uiState.loginRequestState,
                loginFieldState = uiState.loginFieldState,
                passwordFieldState = uiState.passwordFieldState
            )

            LoginForgotPasswordSelection(
                onIntent = viewModel::onIntent,
                loginRequestState = uiState.loginRequestState
            )

            Spacer(modifier = Modifier.size(spacing.lg))

            RegisterSuggestionSelection(
                onIntent = viewModel::onIntent
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            LoginErrorSelection(
                loginRequestState = uiState.loginRequestState
            )

            LoginSendButtonSelection(
                onIntent = viewModel::onIntent,
                isSendButtonEnabled = viewModel.isSendButtonEnabled(),
                loginRequestState = uiState.loginRequestState
            )
        }
    }
}
