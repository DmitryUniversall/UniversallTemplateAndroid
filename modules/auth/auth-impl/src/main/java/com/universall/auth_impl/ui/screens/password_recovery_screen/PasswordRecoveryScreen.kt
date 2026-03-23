package com.universall.auth_impl.ui.screens.password_recovery_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun PasswordRecoveryScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    PasswordRecoveryScreen(
        viewModel = hiltViewModel(),
        innerPadding = innerPadding,
        navController = navController
    )
}

@Composable
internal fun PasswordRecoveryScreen(
    viewModel: PasswordRecoveryScreenViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->

        }
    }
}
