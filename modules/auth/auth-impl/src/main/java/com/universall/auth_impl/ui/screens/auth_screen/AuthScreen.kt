package com.universall.auth_impl.ui.screens.auth_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode.LOGIN
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode.REGISTER
import com.universall.auth_impl.ui.screens.auth_screen.components.AuthModeSelector
import com.universall.auth_impl.ui.screens.auth_screen.components.LoginView
import com.universall.auth_impl.ui.screens.auth_screen.components.RegisterView
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 2 })

    val spacing = Locals.spacing

    // Effects
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is AuthUIEffect.Navigate -> navController.navigate(effect.destination.route)
            }
        }
    }

    // Sync Pager -> ViewModel
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                viewModel.onIntent(AuthUIIntent.SetAuthMode(viewModel.pageToAuthMode(page)))
            }
    }

    // Sync ViewModel -> Pager
    LaunchedEffect(uiState.authMode) {
        val page = viewModel.authModeToPage(uiState.authMode)
        if (page != pagerState.currentPage) pagerState.animateScrollToPage(page)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding() + spacing.screenPadding,
                bottom = innerPadding.calculateBottomPadding() + spacing.screenPadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        AuthModeSelector(
            modifier = Modifier
                .padding(  // TODO: Make an utility to add paddings
                    end = innerPadding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr) + spacing.screenPadding,
                    start = innerPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) + spacing.screenPadding
                ),
            viewModel = viewModel,
            currentMode = uiState.authMode,
            loginRequestState = uiState.loginRequestState,
            registerRequestState = uiState.loginRequestState
        )

        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            userScrollEnabled = true
        ) { page ->
            when (viewModel.pageToAuthMode(page)) {
                LOGIN -> LoginView(input = uiState.loginInputInfo, loginRequestState = uiState.loginRequestState)
                REGISTER -> RegisterView(input = uiState.registerInputInfo, registerRequestState = uiState.registerRequestState)
            }
        }
    }
}
