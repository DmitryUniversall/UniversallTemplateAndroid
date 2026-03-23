package com.universall.auth_impl.ui.screens.register_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.universall.appcore.ui.fields.AppOutlinedField
import com.universall.appcore.ui.fields.FieldState
import com.universall.appcore.ui.state.ResourceState
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.register_screen.RegisterScreenUIIntent

@Composable
internal fun RegisterFormSelection(
    onIntent: (RegisterScreenUIIntent) -> Unit,
    registerRequestState: ResourceState<Unit>,
    loginFieldState: FieldState<String>,
    passwordFieldState: FieldState<String>,
    usernameFieldState: FieldState<String>,
    firstNameFieldState: FieldState<String>,
    lastNameFieldState: FieldState<String>,
) {
    val shapes = Locals.shapes
    val spacing = Locals.spacing

    val fieldShape = shapes.roundedSM

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.login_field),

            state = loginFieldState,
            enabled = loginFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputLogin(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateLoginField) }
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.password_field),

            state = passwordFieldState,
            enabled = loginFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputPassword(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidatePasswordField) }
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.username_field),

            state = usernameFieldState,
            enabled = usernameFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputUsername(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateUsernameField) }
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.first_name_field),

            state = firstNameFieldState,
            enabled = firstNameFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputFirstName(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateFirstNameField) }
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.last_name_field),

            state = lastNameFieldState,
            enabled = lastNameFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputLastName(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateLastNameField) }
        )
    }
}
