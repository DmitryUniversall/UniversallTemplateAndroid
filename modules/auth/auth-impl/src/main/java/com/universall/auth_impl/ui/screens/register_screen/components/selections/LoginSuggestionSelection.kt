package com.universall.auth_impl.ui.screens.register_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.universall.appcore.appcore.ui.buttons.components.TextButton
import com.universall.appcore.appcore.ui.text.components.TextBodyMedium
import com.universall.appcore.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.register_screen.RegisterScreenUIIntent

@Composable
internal fun LoginSuggestionSelection(
    onIntent: (RegisterScreenUIIntent) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBodyMedium(
                text = stringResource(R.string.already_have_an_account),
                color = colors.onBackground
            )

            TextButton(
                text = stringResource(R.string.sign_in_suggestion),
                textColor = colors.primary,

                onClick = {
                    onIntent(RegisterScreenUIIntent.NavigateToLogin)
                }
            )
        }
    }
}
