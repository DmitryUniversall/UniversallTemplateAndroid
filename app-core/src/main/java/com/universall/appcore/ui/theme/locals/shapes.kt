package com.universall.appcore.ui.theme.locals

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

data class Shapes(
    // All corners
    val roundedXXS: Shape,
    val roundedXS: Shape,
    val roundedSM: Shape,
    val roundedMD: Shape,
    val roundedLG: Shape,
    val roundedXL: Shape,
    val roundedXXL: Shape,
    val roundedPill: Shape,

    // Top only
    val roundedTopXXS: Shape,
    val roundedTopXS: Shape,
    val roundedTopSM: Shape,
    val roundedTopMD: Shape,
    val roundedTopLG: Shape,
    val roundedTopXL: Shape,
    val roundedTopXXL: Shape,
    val roundedTopPill: Shape,

    // Bottom only
    val roundedBottomXXS: Shape,
    val roundedBottomXS: Shape,
    val roundedBottomSM: Shape,
    val roundedBottomMD: Shape,
    val roundedBottomLG: Shape,
    val roundedBottomXL: Shape,
    val roundedBottomXXL: Shape,
    val roundedBottomPill: Shape,

    // Start only
    val roundedStartXXS: Shape,
    val roundedStartXS: Shape,
    val roundedStartSM: Shape,
    val roundedStartMD: Shape,
    val roundedStartLG: Shape,
    val roundedStartXL: Shape,
    val roundedStartXXL: Shape,
    val roundedStartPill: Shape,

    // End only
    val roundedEndXXS: Shape,
    val roundedEndXS: Shape,
    val roundedEndSM: Shape,
    val roundedEndMD: Shape,
    val roundedEndLG: Shape,
    val roundedEndXL: Shape,
    val roundedEndXXL: Shape,
    val roundedEndPill: Shape
) {
    companion object {
        fun default(dimens: Dimens) = Shapes(
            // All corners
            roundedXXS = RoundedCornerShape(dimens.xxs),
            roundedXS = RoundedCornerShape(dimens.xs),
            roundedSM = RoundedCornerShape(dimens.sm),
            roundedMD = RoundedCornerShape(dimens.md),
            roundedLG = RoundedCornerShape(dimens.lg),
            roundedXL = RoundedCornerShape(dimens.xl),
            roundedXXL = RoundedCornerShape(dimens.xxl),
            roundedPill = RoundedCornerShape(percent = 50),

            // Top only
            roundedTopXXS = RoundedCornerShape(topStart = dimens.xxs, topEnd = dimens.xxs),
            roundedTopXS = RoundedCornerShape(topStart = dimens.xs, topEnd = dimens.xs),
            roundedTopSM = RoundedCornerShape(topStart = dimens.sm, topEnd = dimens.sm),
            roundedTopMD = RoundedCornerShape(topStart = dimens.md, topEnd = dimens.md),
            roundedTopLG = RoundedCornerShape(topStart = dimens.lg, topEnd = dimens.lg),
            roundedTopXL = RoundedCornerShape(topStart = dimens.xl, topEnd = dimens.xl),
            roundedTopXXL = RoundedCornerShape(topStart = dimens.xxl, topEnd = dimens.xxl),
            roundedTopPill = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50),

            // Bottom only
            roundedBottomXXS = RoundedCornerShape(bottomStart = dimens.xxs, bottomEnd = dimens.xxs),
            roundedBottomXS = RoundedCornerShape(bottomStart = dimens.xs, bottomEnd = dimens.xs),
            roundedBottomSM = RoundedCornerShape(bottomStart = dimens.sm, bottomEnd = dimens.sm),
            roundedBottomMD = RoundedCornerShape(bottomStart = dimens.md, bottomEnd = dimens.md),
            roundedBottomLG = RoundedCornerShape(bottomStart = dimens.lg, bottomEnd = dimens.lg),
            roundedBottomXL = RoundedCornerShape(bottomStart = dimens.xl, bottomEnd = dimens.xl),
            roundedBottomXXL = RoundedCornerShape(bottomStart = dimens.xxl, bottomEnd = dimens.xxl),
            roundedBottomPill = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50),

            // Start only
            roundedStartXXS = RoundedCornerShape(topStart = dimens.xxs, bottomStart = dimens.xxs),
            roundedStartXS = RoundedCornerShape(topStart = dimens.xs, bottomStart = dimens.xs),
            roundedStartSM = RoundedCornerShape(topStart = dimens.sm, bottomStart = dimens.sm),
            roundedStartMD = RoundedCornerShape(topStart = dimens.md, bottomStart = dimens.md),
            roundedStartLG = RoundedCornerShape(topStart = dimens.lg, bottomStart = dimens.lg),
            roundedStartXL = RoundedCornerShape(topStart = dimens.xl, bottomStart = dimens.xl),
            roundedStartXXL = RoundedCornerShape(topStart = dimens.xxl, bottomStart = dimens.xxl),
            roundedStartPill = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50),

            // End only
            roundedEndXXS = RoundedCornerShape(topEnd = dimens.xxs, bottomEnd = dimens.xxs),
            roundedEndXS = RoundedCornerShape(topEnd = dimens.xs, bottomEnd = dimens.xs),
            roundedEndSM = RoundedCornerShape(topEnd = dimens.sm, bottomEnd = dimens.sm),
            roundedEndMD = RoundedCornerShape(topEnd = dimens.md, bottomEnd = dimens.md),
            roundedEndLG = RoundedCornerShape(topEnd = dimens.lg, bottomEnd = dimens.lg),
            roundedEndXL = RoundedCornerShape(topEnd = dimens.xl, bottomEnd = dimens.xl),
            roundedEndXXL = RoundedCornerShape(topEnd = dimens.xxl, bottomEnd = dimens.xxl),
            roundedEndPill = RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50)
        )
    }
}

val LocalShapes = staticCompositionLocalOf<Shapes> {
    error("Shapes not provided")
}
