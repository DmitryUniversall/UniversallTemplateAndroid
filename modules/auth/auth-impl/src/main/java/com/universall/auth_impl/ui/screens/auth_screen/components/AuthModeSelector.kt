package com.universall.auth_impl.ui.screens.auth_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.universall.appcore.ui.objectClickableNoAnimation
import com.universall.appcore.ui.paddingXS
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.text.TextHeadlineSmall
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.appcore.ui.withShapedBackground
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode
import com.universall.auth_impl.ui.screens.auth_screen.AuthUIIntent
import com.universall.auth_impl.ui.screens.auth_screen.AuthViewModel
import kotlin.math.roundToInt

@Composable
private fun Slider(  // TODO: Make universal slider
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    authMode: AuthMode
) {
    val density = LocalDensity.current
    val colors = MaterialTheme.colorScheme
    val shapes = Locals.shapes

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .withShapedBackground(
                color = colors.surface,
                shape = shapes.roundedXS
            )
            .paddingXS()
    ) {
        val thumbWidth = maxWidth / 2
        val thumbWidthPx = with(density) { thumbWidth.toPx() }
        val thumbHeight = maxHeight

        val availableWidthPx = with(density) { maxWidth.toPx() }
        val maxOffsetPx = (availableWidthPx - thumbWidthPx).coerceAtLeast(0f)

        val fraction by animateFloatAsState(
            targetValue = if (authMode == AuthMode.REGISTER) 1f else 0f,
            animationSpec = tween(durationMillis = 320)
        )

        val offsetPx = maxOffsetPx * fraction

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(999f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .objectClickableNoAnimation { viewModel.onIntent(AuthUIIntent.SetAuthMode(AuthMode.LOGIN)) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextHeadlineSmall(
                        text = stringResource(R.string.login),
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .objectClickableNoAnimation { viewModel.onIntent(AuthUIIntent.SetAuthMode(AuthMode.REGISTER)) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextHeadlineSmall(
                        text = stringResource(R.string.register),
                    )
                }
            }

            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetPx.roundToInt(), 0) }
                    .width(thumbWidth)
                    .height(thumbHeight)
                    .withShapedBackground(
                        color = colors.primary,
                        shape = shapes.roundedXS
                    )
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Composable
fun AuthModeSelector(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    currentMode: AuthMode,
    loginRequestState: ResourceState<Unit>,  // TODO: Deny switch when loading
    registerRequestState: ResourceState<Unit>
) {
    Row(
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            viewModel = viewModel,
            authMode = currentMode
        )
    }
}
