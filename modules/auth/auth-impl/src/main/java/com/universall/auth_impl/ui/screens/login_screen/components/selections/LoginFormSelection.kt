package com.universall.auth_impl.ui.screens.login_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.universall.appcore.ui.buttons.generics.components.PrimaryButton
import com.universall.appcore.ui.fields.AppOutlinedField
import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.muted
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isError
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.state.whenHasAnyError
import com.universall.appcore.ui.text.AppText
import com.universall.appcore.ui.text.generics.components.TextBodyLarge
import com.universall.appcore.ui.text.generics.components.TextBodyMedium
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.appcore.utils.asStringOrIfEmpty
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.login_screen.LoginScreenUIIntent

@Composable
internal fun LoginFormSelection(
    onIntent: (LoginScreenUIIntent) -> Unit,
    loginFieldState: FieldState<String>,
    passwordFieldState: FieldState<String>,
    loginRequestState: ResourceState<Unit>,
    isSendButtonEnabled: Boolean
) {
    val colors = MaterialTheme.colorScheme
    val shapes = Locals.shapes
    val spacing = Locals.spacing

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.sm)
        ) {
            AppOutlinedField(
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.roundedSM,
                label = stringResource(R.string.login_field),

                state = loginFieldState,
                enabled = !loginRequestState.isFetching,
                onValueChange = {
                    onIntent(LoginScreenUIIntent.Input.InputLogin(it))
                },
                onUnfocused = {
                    onIntent(LoginScreenUIIntent.Validate.ValidateLoginField(loginFieldState))
                }
            )

            AppOutlinedField(
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.roundedSM,
                label = stringResource(R.string.password_field),

                state = passwordFieldState,
                enabled = !loginRequestState.isFetching,
                onValueChange = {
                    onIntent(LoginScreenUIIntent.Input.InputPassword(it))
                },
                onUnfocused = {
                    onIntent(LoginScreenUIIntent.Validate.ValidatePasswordField(passwordFieldState))
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextBodyLarge(  // TODO: Use button
                    text = stringResource(R.string.forgotPassword),
                    color = colors.primary.muted(alpha = if (!loginRequestState.isFetching) 0.9f else 0.7f),
                    styleOverride = {
                        textDecoration = TextDecoration.Underline
                    }
                )
            }

            loginRequestState.whenHasAnyError { error ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextBodyMedium(
                        text = error.errorMessage.asStringOrIfEmpty { stringResource(R.string.unknown_auth_error) },
                        color = colors.error.muted(if (loginRequestState.isError) 1f else 0.7f),
                        styleOverride = {
                            textAlign = TextAlign.Center
                        }
                    )
                }
            }
        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenPadding),
            enabled = isSendButtonEnabled && !loginRequestState.isFetching,
            onClick = {
                onIntent(LoginScreenUIIntent.SendLogin(loginFieldState.value, passwordFieldState.value))
            }
        ) {
            if (loginRequestState.isFetching) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                )
            } else {
                AppText(text = stringResource(R.string.login_action))  // TODO: Use TextBodyMedium?
            }
        }
    }
}
