package com.universall.auth_impl.ui.screens.auth_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.state.ResourceState
import com.universall.auth_impl.ui.screens.auth_screen.AuthMode
import kotlin.math.roundToInt

@Composable
private fun Slider(  // TODO
    modifier: Modifier = Modifier,
    height: Dp = 56.dp,
    horizontalPadding: Dp = 16.dp,
    trackHeight: Dp = 6.dp,
    thumbSize: Dp = 28.dp,
    trackColor: Color = Color(0xFFDDDDDD),
    progressColor: Color = Color(0xFF1E88E5),
    thumbColor: Color = Color.White,
    animationDuration: Int = 420
) {
    var atEnd by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = Modifier
            .height(100.dp),
    ) {
        val density = LocalDensity.current

        val availableWidthPx = with(density) { (maxWidth - horizontalPadding).toPx() }
        val thumbPx = with(density) { thumbSize.toPx() }
        val maxOffsetPx = (availableWidthPx - thumbPx).coerceAtLeast(0f)
        val fraction by animateFloatAsState(
            targetValue = if (atEnd) 1f else 0f,
            animationSpec = tween(durationMillis = animationDuration)
        )

        val offsetPx = maxOffsetPx * fraction
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetPx.roundToInt(), 0) }
                    .size(thumbSize)
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(8.dp))
                    .background(thumbColor)
            )
        }
    }
}

@Composable
fun AuthModeSelector(
    currentMode: AuthMode,
    loginRequestState: ResourceState<Unit>,
    registerRequestState: ResourceState<Unit>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider()
    }
}
