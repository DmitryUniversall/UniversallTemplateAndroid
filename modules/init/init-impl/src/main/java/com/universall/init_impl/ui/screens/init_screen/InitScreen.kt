package com.universall.init_impl.ui.screens.init_screen

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
import com.universall.appcore.ui.add
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.init_impl.ui.screens.init_screen.components.views.InitScreenErrorView
import com.universall.init_impl.ui.screens.init_screen.components.views.InitScreenLoadingView

@Composable
fun InitScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    InitScreen(
        viewModel = hiltViewModel(),
        innerPadding = innerPadding,
        navController = navController
    )
}

@Composable
internal fun InitScreen(
    viewModel: InitScreenViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    val spacing = Locals.spacing
    val pullRefreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is InitScreenUIEffect.Navigate -> navController.navigate(effect.destination.route) {
                    effect.popUpTo?.let { popUpTo(it.route) { inclusive = effect.inclusive } }
                    launchSingleTop = effect.singleTop
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onIntent(InitScreenUIIntent.RestoreAuthState)
    }

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = pullRefreshState,
        isRefreshing = false,  // Loading indicator is in InitScreenLoadingView
        onRefresh = { viewModel.onIntent(InitScreenUIIntent.Refresh) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding.add(spacing.screenPadding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val currentState = uiState.restoreAuthRequestState) {
                is ResourceState.Idle, is ResourceState.Success<*> -> {}
                is ResourceState.Fetching -> InitScreenLoadingView()
                is ResourceState.Error -> InitScreenErrorView(
                    onIntent = viewModel::onIntent,
                    restoreAuthRequestErrorState = currentState
                )
            }
        }
    }
}
