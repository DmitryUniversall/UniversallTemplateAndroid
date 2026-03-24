package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.universall.appcore.ui.buttons.generics.components.TextButton
import com.universall.appcore.ui.muted
import com.universall.appcore.ui.resources.ResourceState
import com.universall.appcore.ui.resources.isFetching
import com.universall.appcore.ui.text.generics.AppTextDefaults
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.login_screen.LoginScreenUIIntent

@Composable
internal fun LoginForgotPasswordSelection(
    onIntent: (LoginScreenUIIntent) -> Unit,
    loginRequestState: ResourceState<Unit>
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            text = stringResource(R.string.forgotPassword),
            textColor = colors.primary.muted(alpha = if (!loginRequestState.isFetching) 0.9f else 0.7f),
            textStyle = AppTextDefaults.bodyLargeStyle(),
            onClick = { onIntent(LoginScreenUIIntent.NavigateToPasswordRecovery) }
        )
    }
}
