package com.universall.appcore.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.theme.locals.Locals

@Composable fun Modifier.paddingXXXS() = padding(Locals.spacing.xxxs)
@Composable fun Modifier.paddingXXS()  = padding(Locals.spacing.xxs)
@Composable fun Modifier.paddingXS()   = padding(Locals.spacing.xs)
@Composable fun Modifier.paddingSM()   = padding(Locals.spacing.sm)
@Composable fun Modifier.paddingMD()   = padding(Locals.spacing.md)
@Composable fun Modifier.paddingLG()   = padding(Locals.spacing.lg)
@Composable fun Modifier.paddingXL()   = padding(Locals.spacing.xl)
@Composable fun Modifier.paddingXXL()  = padding(Locals.spacing.xxl)
@Composable fun Modifier.paddingXXXL() = padding(Locals.spacing.xxxl)

@Composable fun Modifier.paddingHorizontalXXXS() = padding(horizontal = Locals.spacing.xxxs)
@Composable fun Modifier.paddingHorizontalXXS()  = padding(horizontal = Locals.spacing.xxs)
@Composable fun Modifier.paddingHorizontalXS()   = padding(horizontal = Locals.spacing.xs)
@Composable fun Modifier.paddingHorizontalSM()   = padding(horizontal = Locals.spacing.sm)
@Composable fun Modifier.paddingHorizontalMD()   = padding(horizontal = Locals.spacing.md)
@Composable fun Modifier.paddingHorizontalLG()   = padding(horizontal = Locals.spacing.lg)
@Composable fun Modifier.paddingHorizontalXL()   = padding(horizontal = Locals.spacing.xl)
@Composable fun Modifier.paddingHorizontalXXL()  = padding(horizontal = Locals.spacing.xxl)
@Composable fun Modifier.paddingHorizontalXXXL() = padding(horizontal = Locals.spacing.xxxl)

@Composable fun Modifier.paddingVerticalXXXS() = padding(vertical = Locals.spacing.xxxs)
@Composable fun Modifier.paddingVerticalXXS()  = padding(vertical = Locals.spacing.xxs)
@Composable fun Modifier.paddingVerticalXS()   = padding(vertical = Locals.spacing.xs)
@Composable fun Modifier.paddingVerticalSM()   = padding(vertical = Locals.spacing.sm)
@Composable fun Modifier.paddingVerticalMD()   = padding(vertical = Locals.spacing.md)
@Composable fun Modifier.paddingVerticalLG()   = padding(vertical = Locals.spacing.lg)
@Composable fun Modifier.paddingVerticalXL()   = padding(vertical = Locals.spacing.xl)
@Composable fun Modifier.paddingVerticalXXL()  = padding(vertical = Locals.spacing.xxl)
@Composable fun Modifier.paddingVerticalXXXL() = padding(vertical = Locals.spacing.xxxl)

@Composable fun Modifier.paddingTopXXXS() = padding(top = Locals.spacing.xxxs)
@Composable fun Modifier.paddingTopXXS()  = padding(top = Locals.spacing.xxs)
@Composable fun Modifier.paddingTopXS()   = padding(top = Locals.spacing.xs)
@Composable fun Modifier.paddingTopSM()   = padding(top = Locals.spacing.sm)
@Composable fun Modifier.paddingTopMD()   = padding(top = Locals.spacing.md)
@Composable fun Modifier.paddingTopLG()   = padding(top = Locals.spacing.lg)
@Composable fun Modifier.paddingTopXL()   = padding(top = Locals.spacing.xl)
@Composable fun Modifier.paddingTopXXL()  = padding(top = Locals.spacing.xxl)
@Composable fun Modifier.paddingTopXXXL() = padding(top = Locals.spacing.xxxl)

@Composable fun Modifier.paddingBottomXXXS() = padding(bottom = Locals.spacing.xxxs)
@Composable fun Modifier.paddingBottomXXS()  = padding(bottom = Locals.spacing.xxs)
@Composable fun Modifier.paddingBottomXS()   = padding(bottom = Locals.spacing.xs)
@Composable fun Modifier.paddingBottomSM()   = padding(bottom = Locals.spacing.sm)
@Composable fun Modifier.paddingBottomMD()   = padding(bottom = Locals.spacing.md)
@Composable fun Modifier.paddingBottomLG()   = padding(bottom = Locals.spacing.lg)
@Composable fun Modifier.paddingBottomXL()   = padding(bottom = Locals.spacing.xl)
@Composable fun Modifier.paddingBottomXXL()  = padding(bottom = Locals.spacing.xxl)
@Composable fun Modifier.paddingBottomXXXL() = padding(bottom = Locals.spacing.xxxl)

@Composable fun Modifier.paddingStartXXXS() = padding(start = Locals.spacing.xxxs)
@Composable fun Modifier.paddingStartXXS()  = padding(start = Locals.spacing.xxs)
@Composable fun Modifier.paddingStartXS()   = padding(start = Locals.spacing.xs)
@Composable fun Modifier.paddingStartSM()   = padding(start = Locals.spacing.sm)
@Composable fun Modifier.paddingStartMD()   = padding(start = Locals.spacing.md)
@Composable fun Modifier.paddingStartLG()   = padding(start = Locals.spacing.lg)
@Composable fun Modifier.paddingStartXL()   = padding(start = Locals.spacing.xl)
@Composable fun Modifier.paddingStartXXL()  = padding(start = Locals.spacing.xxl)
@Composable fun Modifier.paddingStartXXXL() = padding(start = Locals.spacing.xxxl)

@Composable fun Modifier.paddingEndXXXS() = padding(end = Locals.spacing.xxxs)
@Composable fun Modifier.paddingEndXXS()  = padding(end = Locals.spacing.xxs)
@Composable fun Modifier.paddingEndXS()   = padding(end = Locals.spacing.xs)
@Composable fun Modifier.paddingEndSM()   = padding(end = Locals.spacing.sm)
@Composable fun Modifier.paddingEndMD()   = padding(end = Locals.spacing.md)
@Composable fun Modifier.paddingEndLG()   = padding(end = Locals.spacing.lg)
@Composable fun Modifier.paddingEndXL()   = padding(end = Locals.spacing.xl)
@Composable fun Modifier.paddingEndXXL()  = padding(end = Locals.spacing.xxl)
@Composable fun Modifier.paddingEndXXXL() = padding(end = Locals.spacing.xxxl)

@Composable fun Modifier.paddingScreen() = this.padding(vertical = Locals.spacing.screenPadding, horizontal = Locals.spacing.screenPadding)
@Composable fun Modifier.paddingHorizontalScreen() = this.padding(horizontal = Locals.spacing.screenPadding)
@Composable fun Modifier.paddingVerticalScreen() = this.padding(vertical = Locals.spacing.screenPadding)


@Composable
fun PaddingValues.add(all: Dp = 0.dp): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(layoutDirection) + all,
        top = calculateTopPadding() + all,
        end = calculateEndPadding(layoutDirection) + all,
        bottom = calculateBottomPadding() + all
    )
}

@Composable
fun PaddingValues.add(horizontal: Dp = 0.dp, vertical: Dp = 0.dp): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(layoutDirection) + horizontal,
        top = calculateTopPadding() + vertical,
        end = calculateEndPadding(layoutDirection) + horizontal,
        bottom = calculateBottomPadding() + vertical
    )
}
