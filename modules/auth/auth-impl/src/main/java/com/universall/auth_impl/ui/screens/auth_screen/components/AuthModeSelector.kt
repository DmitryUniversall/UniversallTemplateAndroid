package com.universall.auth_impl.ui.screens.auth_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.muted
import com.universall.appcore.ui.objectClickableNoAnimation
import com.universall.appcore.ui.paddingXS
import com.universall.appcore.ui.slider.Slider
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.text.generics.components.TextHeadlineSmall
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.appcore.ui.withShapedBackground
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode
import com.universall.auth_impl.ui.screens.auth_screen.AuthUIIntent
import com.universall.auth_impl.ui.screens.auth_screen.AuthViewModel

@Composable
private fun AuthModeSlider(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    authMode: AuthMode,
    enabled: Boolean,
    animationSpec: AnimationSpec<Float>
) {
    val colors = MaterialTheme.colorScheme
    val shapes = Locals.shapes

    val loginTextColor by animateColorAsState(
        targetValue = if (authMode == AuthMode.LOGIN) colors.onPrimary else colors.primary
    )

    val registerTextColor by animateColorAsState(
        targetValue = if (authMode == AuthMode.REGISTER) colors.onPrimary else colors.primary
    )

    Slider(
        modifier = modifier
            .fillMaxSize()
            .withShapedBackground(
                color = colors.surface,
                shape = shapes.roundedXS
            )
            .paddingXS(),
        totalSteps = 2,
        selectedStep = if (authMode == AuthMode.LOGIN) 0 else 1,
        animationSpec = animationSpec,
        sliderColor = if (enabled) colors.primary else colors.primary.muted(),
        sliderShape = shapes.roundedXS
    ) { step ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .objectClickableNoAnimation {
                    if (!enabled) return@objectClickableNoAnimation

                    viewModel.onIntent(
                        AuthUIIntent.SetAuthMode(
                            if (step == 0) AuthMode.LOGIN else AuthMode.REGISTER
                        )
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            when (step) {
                0 -> {
                    TextHeadlineSmall(
                        text = stringResource(R.string.login),
                        color = loginTextColor
                    )
                }

                1 -> {
                    TextHeadlineSmall(
                        text = stringResource(R.string.register),
                        color = registerTextColor
                    )
                }
            }
        }
    }
}

@Composable
fun AuthModeSelector(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    currentMode: AuthMode,
    loginRequestState: ResourceState<Unit>,
    registerRequestState: ResourceState<Unit>,
    animationSpec: AnimationSpec<Float>
) {
    Row(
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthModeSlider(
            viewModel = viewModel,
            authMode = currentMode,
            enabled = !(loginRequestState.isFetching || registerRequestState.isFetching),
            animationSpec = animationSpec
        )
    }
}
