package com.universall.main.ui.screens.main_screen.views.profile_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ProfileView(
    innerPadding: PaddingValues,
    navController: NavController
) {
    ProfileView(
        viewModel = hiltViewModel(),
        innerPadding = innerPadding,
        navController = navController
    )
}

@Composable
internal fun ProfileView(
    viewModel: ProfileViewViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile view")
    }
}
