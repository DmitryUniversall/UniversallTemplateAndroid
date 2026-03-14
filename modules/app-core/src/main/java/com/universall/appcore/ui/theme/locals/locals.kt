package com.universall.appcore.ui.theme.locals

import androidx.compose.runtime.Composable


object Locals {
    val dimens: Dimens
        @Composable
        get() = LocalDimens.current

    val shapes: Shapes
        @Composable
        get() = LocalShapes.current

    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current

    val extraColors: ExtraColors
        @Composable
        get() = LocalExtraColors.current

    val gradients: Gradients
        @Composable
        get() = LocalGradients.current
}
