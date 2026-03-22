package com.universall.auth_impl.ui.screens.register_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

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
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->

        }
    }
}
