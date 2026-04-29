package com.universall.appcore.base.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Immutable
data class AppTextStyle(
    val textStyle: TextStyle,
    val textAlign: TextAlign? = null,
    val overflow: TextOverflow = TextOverflow.Clip,
    val softWrap: Boolean = true,
    val maxLines: Int = Int.MAX_VALUE,
    val minLines: Int = 1,
    val onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    class Builder(
        var fontSize: TextUnit? = null,
        var fontWeight: FontWeight? = null,
        var fontStyle: FontStyle? = null,
        var fontFamily: FontFamily? = null,
        var letterSpacing: TextUnit? = null,
        var textDecoration: TextDecoration? = null,
        var textAlign: TextAlign? = null,
        var overflow: TextOverflow = TextOverflow.Clip,
        var softWrap: Boolean = true,
        var maxLines: Int = Int.MAX_VALUE,
        var minLines: Int = 1,
        var onTextLayout: ((TextLayoutResult) -> Unit)? = null,
        private var baseTextStyle: TextStyle = TextStyle.Default
    ) {
        fun fromStyle(style: AppTextStyle) = apply {
            fromTextStyle(style.textStyle)
            textAlign = style.textAlign
            overflow = style.overflow
            softWrap = style.softWrap
            maxLines = style.maxLines
            minLines = style.minLines
            onTextLayout = style.onTextLayout
        }

        fun fromTextStyle(textStyle: TextStyle) = apply {
            baseTextStyle = textStyle
            fontSize = textStyle.fontSize
            fontWeight = textStyle.fontWeight
            fontStyle = textStyle.fontStyle
            fontFamily = textStyle.fontFamily
            letterSpacing = textStyle.letterSpacing
            textDecoration = textStyle.textDecoration
        }

        fun build(): AppTextStyle = AppTextStyle(
            textStyle = baseTextStyle.copy(
                fontSize = fontSize ?: baseTextStyle.fontSize,
                fontWeight = fontWeight ?: baseTextStyle.fontWeight,
                fontStyle = fontStyle ?: baseTextStyle.fontStyle,
                fontFamily = fontFamily ?: baseTextStyle.fontFamily,
                letterSpacing = letterSpacing ?: baseTextStyle.letterSpacing,
                textDecoration = textDecoration ?: baseTextStyle.textDecoration
            ),
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout
        )
    }
}

fun AppTextStyle.toBuilder(): AppTextStyle.Builder =
    AppTextStyle.Builder().fromStyle(this)

inline fun AppTextStyle.override(
    block: AppTextStyle.Builder.() -> Unit
): AppTextStyle = toBuilder().apply(block).build()
