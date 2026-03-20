package com.universall.template.ui.screens.init_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.template.ui.navigation.destinations.InitDestination
import com.universall.template.ui.screens.init_screen.components.InitErrorView
import com.universall.template.ui.screens.init_screen.components.InitLoadingView

@Composable
fun InitScreen(
    viewModel: InitScreenViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navController: NavController
) {
    val spacing = Locals.spacing

    val uiState by viewModel.uiState.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(InitIntent.RestoreAuthState)
    }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is InitUIEffect.Navigate -> navController.navigate(effect.destination.route) {
                    popUpTo(InitDestination.route) { inclusive = true }
                }
            }
        }
    }

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = pullRefreshState,
        isRefreshing = false,
        onRefresh = {
            if (!uiState.restoreAuthRequestState.isFetching) viewModel.onIntent(InitIntent.Refresh)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.screenPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val currentState = uiState.restoreAuthRequestState) {
                is ResourceState.Idle, is ResourceState.Success<*> -> {}
                is ResourceState.Fetching<*> -> InitLoadingView()
                is ResourceState.Error<*> -> InitErrorView(viewModel, currentState)
            }
        }
    }
}
