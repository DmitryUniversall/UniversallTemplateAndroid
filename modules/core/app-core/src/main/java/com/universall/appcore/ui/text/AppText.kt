package com.universall.appcore.ui.text

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    style: AppTextStyle = AppTextStyle.Builder().fromTextStyle(LocalTextStyle.current).build(),
    color: Color = LocalContentColor.current,
    styleOverrides: (AppTextStyle.Builder.() -> Unit)? = null
) {
    val finalStyle = styleOverrides?.let { style.override(it) } ?: style

    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = finalStyle.textStyle,
        textAlign = finalStyle.textAlign,
        overflow = finalStyle.overflow,
        softWrap = finalStyle.softWrap,
        maxLines = finalStyle.maxLines,
        minLines = finalStyle.minLines,
        onTextLayout = finalStyle.onTextLayout
    )
}
