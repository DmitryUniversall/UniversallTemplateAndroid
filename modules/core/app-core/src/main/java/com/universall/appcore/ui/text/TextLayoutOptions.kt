package com.universall.appcore.ui.text

import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

data class TextLayoutOptions(
    val textAlign: TextAlign? = null,
    val overflow: TextOverflow = TextOverflow.Clip,
    val softWrap: Boolean = true,
    val maxLines: Int = Int.MAX_VALUE,
    val minLines: Int = 1,
    val onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    class Builder(
        var textAlign: TextAlign? = null,
        var overflow: TextOverflow = TextOverflow.Clip,
        var softWrap: Boolean = true,
        var maxLines: Int = Int.MAX_VALUE,
        var minLines: Int = 1,
        var onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) {
        fun build() = TextLayoutOptions(
            textAlign,
            overflow,
            softWrap,
            maxLines,
            minLines,
            onTextLayout
        )
    }
}
