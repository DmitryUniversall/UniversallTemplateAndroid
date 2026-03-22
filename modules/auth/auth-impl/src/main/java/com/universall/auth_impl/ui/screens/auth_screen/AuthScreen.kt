package com.universall.auth_impl.ui.screens.auth_screen

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.universall.appcore.ui.buttons.generics.components.PrimaryButton
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.text.generics.components.TextBodyMedium
import com.universall.appcore.ui.text.generics.components.TextDisplayLarge
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode.LOGIN
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode.REGISTER
import com.universall.auth_impl.ui.screens.auth_screen.components.AuthModeSelector
import com.universall.auth_impl.ui.screens.auth_screen.views.login_view.LoginView
import com.universall.auth_impl.ui.screens.auth_screen.views.register_view.RegisterView
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing
    val pageSwitchAnimationSpec: AnimationSpec<Float> = tween(durationMillis = 200)

    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 2 })
    val focusManager = LocalFocusManager.current

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
        if (page != pagerState.currentPage) pagerState.animateScrollToPage(
            page = page,
            animationSpec = pageSwitchAnimationSpec
        )
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
            .padding(
                top = innerPadding.calculateTopPadding() + spacing.screenPadding,
                bottom = innerPadding.calculateBottomPadding() + spacing.screenPadding
            )
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.lg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            TextDisplayLarge(
                text = stringResource(R.string.welcome_back),
                color = colors.onBackground,
                styleOverride = {
                    fontWeight = FontWeight.Bold
                }
            )

            TextBodyMedium(
                text = stringResource(R.string.log_in_to_continue),
                color = colors.onBackground
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            AuthModeSelector(
                modifier = Modifier
                    .padding(horizontal = spacing.screenPadding),
                animationSpec = pageSwitchAnimationSpec,
                viewModel = viewModel,
                inputEnabled = uiState.isInputEnabled,
                currentMode = uiState.authMode
            )

            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                userScrollEnabled = false,  // TODO: Sync animations?
                verticalAlignment = Alignment.Top
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = spacing.screenPadding),
                ) {
                    when (viewModel.pageToAuthMode(page)) {
                        LOGIN -> LoginView(viewModel = viewModel, input = uiState.loginInputInfo, loginRequestState = uiState.loginRequestState)
                        REGISTER -> RegisterView(viewModel = viewModel, input = uiState.registerInputInfo, registerRequestState = uiState.registerRequestState)
                    }
                }
            }

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.screenPadding),
                enabled = uiState.isSendButtonEnabled && !uiState.isFetchingAnything,
                onClick = {
                    if (uiState.authMode == LOGIN) {
                        viewModel.onIntent(AuthUIIntent.SendLogin(uiState.loginInputInfo))
                    } else {
                        viewModel.onIntent(AuthUIIntent.SendRegister(uiState.registerInputInfo))
                    }
                }
            ) {
                if (uiState.isFetchingAnything) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    AppText(
                        text = if (uiState.authMode == LOGIN) stringResource(R.string.login_action) else stringResource(R.string.register_action),
                    )
                }
            }
        }
    }
}
