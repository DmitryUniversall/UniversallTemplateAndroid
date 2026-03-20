package com.universall.appcore.ui.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppText(
    text: String,
    style: AppTextStyle,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    styleOverrides: (AppTextStyle.Builder.() -> Unit)? = null
) {
    val finalStyle = styleOverrides?.let { style.override(it) } ?: style

    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.textStyle,
        textAlign = finalStyle.textAlign,
        overflow = finalStyle.overflow,
        softWrap = finalStyle.softWrap,
        maxLines = finalStyle.maxLines,
        minLines = finalStyle.minLines,
        onTextLayout = finalStyle.onTextLayout
    )
}
