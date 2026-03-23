package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.buttons.generics.components.PrimaryButton
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.login_screen.LoginScreenUIIntent

@Composable
internal fun LoginSendButtonSelection(
    onIntent: (LoginScreenUIIntent) -> Unit,
    isSendButtonEnabled: Boolean,
    loginRequestState: ResourceState<Unit>
) {
    val spacing = Locals.spacing

    PrimaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenPadding),
        enabled = isSendButtonEnabled,
        onClick = { onIntent(LoginScreenUIIntent.SendLogin) }
    ) {
        if (loginRequestState.isFetching) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp
            )
        } else {
            AppText(text = stringResource(R.string.login_action))
        }
    }
}
