package com.universall.template.ui.screens.init_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.state.isRefreshing
import com.universall.appcore.ui.text.TextHeadlineLarge
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.template.R
import com.universall.template.ui.navigation.destinations.InitDestination
import com.universall.template.ui.screens.init_screen.components.InitErrorView
import com.universall.template.ui.screens.init_screen.components.InitLoadingView
import com.universall.template.ui.screens.init_screen.components.InitSuccessView

@Composable
fun InitScreen(  // TODO: Fix screen blink when init performed too fast
    viewModel: InitScreenViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val spacing = Locals.spacing

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
            .padding(
                top = innerPadding.calculateTopPadding() + spacing.screenPadding,
                end = innerPadding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr) + spacing.screenPadding,
                bottom = innerPadding.calculateBottomPadding() + spacing.screenPadding,
                start = innerPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) + spacing.screenPadding
            ),
        isRefreshing = uiState.restoreAuthRequestState.isRefreshing,
        onRefresh = {
            if (!uiState.restoreAuthRequestState.isFetching) viewModel.onIntent(InitIntent.Refresh)
        },
        contentAlignment = Alignment.TopCenter
    ) {
        BoxWithConstraints {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = maxHeight * 0.25f),
                contentAlignment = Alignment.Center
            ) {
                TextHeadlineLarge(
                    text = stringResource(R.string.init_welcome),
                    styleOverride = {
                        fontWeight = FontWeight.Bold
                    }
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                when (val currentState = uiState.restoreAuthRequestState) {
                    is ResourceState.Idle -> {}
                    is ResourceState.Fetching<*> -> InitLoadingView()
                    is ResourceState.Error<*> -> InitErrorView(viewModel, currentState)
                    is ResourceState.Success<*> -> InitSuccessView()
                }
            }
        }
    }
}
