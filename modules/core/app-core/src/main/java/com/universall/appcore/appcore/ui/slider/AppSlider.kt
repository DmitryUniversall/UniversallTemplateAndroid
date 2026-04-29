package com.universall.appcore.appcore.ui.slider

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import com.universall.appcore.appcore.ui.utils.withShapedBackground
import kotlin.math.roundToInt

@Composable
fun AppSlider(
    modifier: Modifier = Modifier,
    totalSteps: Int,
    selectedStep: Int,
    animationSpec: AnimationSpec<Float>,
    sliderColor: Color,
    sliderShape: Shape,
    block: @Composable (step: Int) -> Unit
) {
    assert(totalSteps > selectedStep)
    assert(selectedStep >= 0)

    val density = LocalDensity.current

    BoxWithConstraints(modifier = modifier) {
        val sliderWidth = maxWidth / totalSteps
        val sliderWidthPx = with(density) { sliderWidth.toPx() }
        val sliderHeight = maxHeight

        val availableWidthPx = with(density) { maxWidth.toPx() }
        val maxOffsetPx = (availableWidthPx - sliderWidthPx).coerceAtLeast(0f)

        val fraction by animateFloatAsState(
            targetValue = selectedStep.toFloat() / (totalSteps - 1),
            animationSpec = animationSpec
        )

        val offsetPx = maxOffsetPx * fraction

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetPx.roundToInt(), 0) }
                    .width(sliderWidth)
                    .height(sliderHeight)
                    .withShapedBackground(
                        color = sliderColor,
                        shape = sliderShape
                    )
                    .align(Alignment.CenterStart)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(999f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(totalSteps) { step ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        block(step)
                    }
                }
            }
        }
    }
}
