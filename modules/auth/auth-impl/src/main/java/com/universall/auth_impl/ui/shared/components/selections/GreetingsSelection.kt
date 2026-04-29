package com.universall.auth_impl.ui.shared.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.universall.appcore.appcore.ui.text.components.TextBodyMedium
import com.universall.appcore.appcore.ui.text.components.TextDisplayLarge
import com.universall.appcore.appcore.ui.theme.locals.Locals

@Composable
internal fun GreetingsSelection(
    titleText: String,
    actionText: String
) {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        TextDisplayLarge(
            text = titleText,
            color = colors.onBackground,
            styleOverride = { fontWeight = FontWeight.Bold }
        )

        TextBodyMedium(
            text = actionText,
            color = colors.onBackground
        )
    }
}
