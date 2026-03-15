package com.universall.appcore.ui.text

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

data class TextStyleOverrides(
    val fontSize: TextUnit? = null,
    val fontWeight: FontWeight? = null,
    val fontStyle: FontStyle? = null,
    val fontFamily: FontFamily? = null,
    val letterSpacing: TextUnit? = null,
    val textDecoration: TextDecoration? = null
) {
    fun applyTo(base: TextStyle): TextStyle = base.copy(
        fontSize = fontSize ?: base.fontSize,
        fontWeight = fontWeight ?: base.fontWeight,
        fontStyle = fontStyle ?: base.fontStyle,
        fontFamily = fontFamily ?: base.fontFamily,
        letterSpacing = letterSpacing ?: base.letterSpacing,
        textDecoration = textDecoration ?: base.textDecoration
    )

    class Builder(
        var fontSize: TextUnit? = null,
        var fontWeight: FontWeight? = null,
        var fontStyle: FontStyle? = null,
        var fontFamily: FontFamily? = null,
        var letterSpacing: TextUnit? = null,
        var textDecoration: TextDecoration? = null
    ) {
        fun build() = TextStyleOverrides(
            fontSize,
            fontWeight,
            fontStyle,
            fontFamily,
            letterSpacing,
            textDecoration
        )
    }
}
