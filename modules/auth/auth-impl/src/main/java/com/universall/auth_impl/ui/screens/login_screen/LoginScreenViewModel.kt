package com.universall.auth_impl.ui.screens.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.appcore.ui.fields.validators.validateStringLength
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.state.toError
import com.universall.appcore.ui.state.toLoading
import com.universall.appcore.ui.state.toSuccess
import com.universall.appcore.utils.UIString
import com.universall.appcore.utils.logError
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.usecases.LoginUseCase
import com.universall.auth_impl.ui.navigation.AuthDestination
import com.universall.auth_impl.ui.navigation.PasswordRecoveryDestination
import com.universall.auth_impl.ui.navigation.RegisterDestination
import com.universall.core.utils.messageOrDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<LoginScreenUIEffect>()
    val effects = _effects.asSharedFlow()

    private suspend fun login(schema: LoginSchema) {
        if (_uiState.value.loginRequestState.isFetching) {
            this.logWarn { "Called login during fetch, ignoring" }
            return
        }

        _uiState.update { it.copy(loginRequestState = it.loginRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                loginRequestState = loginUseCase.invoke(schema)
                    .fold(
                        onSuccess = { state.loginRequestState.toSuccess(Unit) },
                        onFailure = { error ->
                            this.logError(error) { "Unexpected error occurred" }
                            state.loginRequestState.toError(UIString.of(error.messageOrDefault("Unknown auth error")), error)
                        }
                    )
            )
        }
    }

    private fun launchSendLogin(login: String, password: String) {
        viewModelScope.launch {
            login(schema = LoginSchema(login, password))
        }
    }

    private fun processValidateIntent(intent: LoginScreenUIIntent.Validate) {
        when (intent) {
            is LoginScreenUIIntent.Validate.ValidateLoginField -> _uiState.update { state ->
                state.copy(
                    loginFieldState = state.loginFieldState.validate {
                        errorMessage = validateStringLength(minLength = 3, maxLength = 100) ?: UIString.empty()
                    }
                )
            }

            is LoginScreenUIIntent.Validate.ValidatePasswordField -> _uiState.update { state ->
                state.copy(
                    passwordFieldState = state.passwordFieldState.validate {
                        errorMessage = validateStringLength(minLength = 8, maxLength = 100) ?: UIString.empty()
                    }
                )
            }
        }
    }

    private fun processInputIntent(intent: LoginScreenUIIntent.Input) {
        when (intent) {
            is LoginScreenUIIntent.Input.InputLogin -> _uiState.update { it.copy(loginFieldState = it.loginFieldState.setNewValue(intent.value)) }
            is LoginScreenUIIntent.Input.InputPassword -> _uiState.update { it.copy(passwordFieldState = it.passwordFieldState.setNewValue(intent.value)) }
        }
    }

    fun isSendButtonEnabled(): Boolean =
        _uiState.value.loginFieldState.isOk() &&
                _uiState.value.passwordFieldState.isOk() &&
                !_uiState.value.loginRequestState.isFetching

    fun onIntent(intent: LoginScreenUIIntent) {
        when (intent) {
            is LoginScreenUIIntent.NavigateToRegister -> viewModelScope.launch { _effects.emit(LoginScreenUIEffect.Navigate(RegisterDestination, popUpTo = AuthDestination)) }
            is LoginScreenUIIntent.NavigateToPasswordRecovery -> viewModelScope.launch { _effects.emit(LoginScreenUIEffect.Navigate(PasswordRecoveryDestination, popUpTo = AuthDestination)) }

            is LoginScreenUIIntent.Validate -> processValidateIntent(intent)
            is LoginScreenUIIntent.Input -> processInputIntent(intent)

            is LoginScreenUIIntent.SendLogin -> launchSendLogin(
                login = uiState.value.loginFieldState.value,
                password = uiState.value.passwordFieldState.value
            )
        }
    }
}
