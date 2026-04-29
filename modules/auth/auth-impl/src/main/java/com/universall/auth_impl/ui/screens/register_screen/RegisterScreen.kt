package com.universall.auth_impl.ui.screens.register_screen

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
import com.universall.appcore.appcore.ui.utils.add
import com.universall.appcore.appcore.ui.utils.clearFocusOnTap
import com.universall.appcore.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.register_screen.components.selections.LoginSuggestionSelection
import com.universall.auth_impl.ui.screens.register_screen.components.selections.RegisterErrorSelection
import com.universall.auth_impl.ui.screens.register_screen.components.selections.RegisterFormSelection
import com.universall.auth_impl.ui.screens.register_screen.components.selections.RegisterSendButtonSelection
import com.universall.auth_impl.ui.shared.components.selections.GreetingsSelection

@Composable
fun RegisterScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    RegisterScreen(
        viewModel = hiltViewModel(),
        innerPadding = innerPadding,
        navController = navController
    )
}

@Composable
internal fun RegisterScreen(
    viewModel: RegisterScreenViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    val spacing = Locals.spacing

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is RegisterScreenUIEffect.Navigate -> navController.navigate(effect.destination.route) {
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
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            GreetingsSelection(
                titleText = stringResource(R.string.welcome),
                actionText = stringResource(R.string.register_to_continue)
            )

            RegisterFormSelection(
                onIntent = viewModel::onIntent,
                registerRequestState = uiState.registerRequestState,
                loginFieldState = uiState.loginFieldState,
                passwordFieldState = uiState.passwordFieldState,
                usernameFieldState = uiState.usernameFieldState,
                firstNameFieldState = uiState.firstNameFieldState,
                lastNameFieldState = uiState.lastNameFieldState
            )

            Spacer(modifier = Modifier.size(spacing.lg))

            LoginSuggestionSelection(
                onIntent = viewModel::onIntent
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            RegisterErrorSelection(
                registerRequestState = uiState.registerRequestState
            )

            RegisterSendButtonSelection(
                onIntent = viewModel::onIntent,
                isSendButtonEnabled = uiState.isSendButtonEnabled,
                registerRequestState = uiState.registerRequestState
            )
        }
    }
}
