package com.universall.appcore.base.ui.buttons

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppButtonColors(
    val container: Color,
    val content: Color,
    val containerPressed: Color,
    val contentPressed: Color,
    val containerDisabled: Color,
    val contentDisabled: Color,
    val border: Color? = null,
    val borderPressed: Color? = null,
    val borderDisabled: Color? = null
) {
    class Builder(
        var container: Color? = null,
        var content: Color? = null,
        var containerPressed: Color? = null,
        var contentPressed: Color? = null,
        var containerDisabled: Color? = null,
        var contentDisabled: Color? = null,
        var border: Color? = null,
        var borderPressed: Color? = null,
        var borderDisabled: Color? = null
    ) {
        fun fromColors(colors: AppButtonColors) = apply {
            container = colors.container
            content = colors.content
            containerPressed = colors.containerPressed
            contentPressed = colors.contentPressed
            containerDisabled = colors.containerDisabled
            contentDisabled = colors.contentDisabled
            border = colors.border
            borderPressed = colors.borderPressed
            borderDisabled = colors.borderDisabled
        }

        fun build(): AppButtonColors = AppButtonColors(
            container = requireNotNull(container) { "container is required" },
            content = requireNotNull(content) { "content is required" },
            containerPressed = requireNotNull(containerPressed) { "containerPressed is required" },
            contentPressed = requireNotNull(contentPressed) { "contentPressed is required" },
            containerDisabled = requireNotNull(containerDisabled) { "containerDisabled is required" },
            contentDisabled = requireNotNull(contentDisabled) { "contentDisabled is required" },
            border = border,
            borderPressed = borderPressed,
            borderDisabled = borderDisabled
        )
    }
}


fun AppButtonColors.toBuilder(): AppButtonColors.Builder =
    AppButtonColors.Builder().fromColors(this)

inline fun AppButtonColors.override(
    block: AppButtonColors.Builder.() -> Unit
): AppButtonColors = toBuilder().apply(block).build()
