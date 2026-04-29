package com.universall.auth_impl.ui.screens.register_screen.components.selections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.universall.appcore.base.ui.fields.AppOutlinedField
import com.universall.appcore.appcore.ui.fields.components.PasswordOutlinedField
import com.universall.appcore.appcore.ui.fields.state.TextFieldState
import com.universall.appcore.appcore.ui.state.RequestState
import com.universall.appcore.appcore.ui.state.isFetching
import com.universall.appcore.appcore.ui.theme.locals.Locals
import com.universall.auth_impl.R
import com.universall.auth_impl.ui.screens.register_screen.RegisterScreenUIIntent

@Composable
internal fun RegisterFormSelection(
    onIntent: (RegisterScreenUIIntent) -> Unit,
    registerRequestState: RequestState<Unit>,
    loginFieldState: TextFieldState,
    passwordFieldState: TextFieldState,
    usernameFieldState: TextFieldState,
    firstNameFieldState: TextFieldState,
    lastNameFieldState: TextFieldState,
) {
    val shapes = Locals.shapes
    val spacing = Locals.spacing

    val fieldShape = shapes.roundedSM

    val focusManager = LocalFocusManager.current

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
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateLoginField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        PasswordOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.password_field),

            state = passwordFieldState,
            enabled = loginFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputPassword(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidatePasswordField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.username_field),

            state = usernameFieldState,
            enabled = usernameFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputUsername(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateUsernameField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.first_name_field),

            state = firstNameFieldState,
            enabled = firstNameFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputFirstName(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateFirstNameField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        AppOutlinedField(
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            label = stringResource(R.string.last_name_field),

            state = lastNameFieldState,
            enabled = lastNameFieldState.enabled && !registerRequestState.isFetching,
            onValueChange = { onIntent(RegisterScreenUIIntent.Input.InputLastName(it)) },
            onUnfocused = { onIntent(RegisterScreenUIIntent.Validate.ValidateLastNameField) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
}
