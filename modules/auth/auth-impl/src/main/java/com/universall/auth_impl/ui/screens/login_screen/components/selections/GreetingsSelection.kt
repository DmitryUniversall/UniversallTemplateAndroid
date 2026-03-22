package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.universall.appcore.ui.text.generics.components.TextBodyMedium
import com.universall.appcore.ui.text.generics.components.TextDisplayLarge
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R

@Composable
internal fun GreetingsSelection() {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        TextDisplayLarge(
            text = stringResource(R.string.welcome_back),
            color = colors.onBackground,
            styleOverride = {
                fontWeight = FontWeight.Bold
            }
        )

        TextBodyMedium(
            text = stringResource(R.string.log_in_to_continue),
            color = colors.onBackground
        )
    }
}
