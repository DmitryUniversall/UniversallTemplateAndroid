package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.universall.appcore.ui.text.generics.components.TextBodyMedium
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R

@Composable
internal fun RegisterSuggestionSelection() {
    val colors = MaterialTheme.colorScheme
    val spacing = Locals.spacing

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
        ) {
            TextBodyMedium(
                text = stringResource(R.string.have_no_account),
                color = colors.onBackground
            )

            TextBodyMedium(  // TODO: Use button
                text = stringResource(R.string.sign_in_suggestion),
                color = colors.primary
            )
        }
    }
}
