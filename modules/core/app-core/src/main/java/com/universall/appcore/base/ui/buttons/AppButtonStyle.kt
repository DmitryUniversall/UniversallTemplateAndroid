package com.universall.appcore.base.ui.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Immutable
data class AppButtonStyle(
    val shape: Shape,
    val minWidth: Dp,
    val minHeight: Dp,
    val contentPadding: PaddingValues,
    val elevation: Dp,
    val pressedElevation: Dp,
    val borderWidth: Dp,
    val colors: AppButtonColors,
) {
    class Builder(
        var shape: Shape? = null,
        var minWidth: Dp? = null,
        var minHeight: Dp? = null,
        var contentPadding: PaddingValues? = null,
        var elevation: Dp? = null,
        var pressedElevation: Dp? = null,
        var borderWidth: Dp? = null,
        var colors: AppButtonColors? = null
    ) {
        fun fromStyle(style: AppButtonStyle) = apply {
            shape = style.shape
            minWidth = style.minWidth
            minHeight = style.minHeight
            contentPadding = style.contentPadding
            elevation = style.elevation
            pressedElevation = style.pressedElevation
            borderWidth = style.borderWidth
            colors = style.colors
        }

        fun build(): AppButtonStyle = AppButtonStyle(
            shape = requireNotNull(shape) { "shape is required" },
            minWidth = requireNotNull(minWidth) { "minWidth is required" },
            minHeight = requireNotNull(minHeight) { "minHeight is required" },
            contentPadding = requireNotNull(contentPadding) { "contentPadding is required" },
            elevation = requireNotNull(elevation) { "elevation is required" },
            pressedElevation = requireNotNull(pressedElevation) { "pressedElevation is required" },
            borderWidth = requireNotNull(borderWidth) { "borderWidth is required" },
            colors = requireNotNull(colors) { "colors is required" }
        )
    }
}

fun AppButtonStyle.toBuilder(): AppButtonStyle.Builder =
    AppButtonStyle.Builder().fromStyle(this)

inline fun AppButtonStyle.override(
    block: AppButtonStyle.Builder.() -> Unit
): AppButtonStyle = toBuilder().apply(block).build()
