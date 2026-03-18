package com.universall.auth_impl.ui.screens.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.appcore.ui.state.isFetching
import com.universall.appcore.ui.state.toError
import com.universall.appcore.ui.state.toLoading
import com.universall.appcore.ui.state.toSuccess
import com.universall.appcore.utils.logWarn
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_api.domain.usecases.LoginUseCase
import com.universall.auth_api.domain.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<AuthUIEffect>()
    val effects = _effects.asSharedFlow()

    private suspend fun login(schema: LoginSchema) {
        if (_uiState.value.loginRequestState.isFetching || _uiState.value.registerRequestState.isFetching) {
            this.logWarn { "Called login during fetch, ignoring" }
            return
        }

        _uiState.update { it.copy(loginRequestState = it.loginRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                loginRequestState = loginUseCase.invoke(schema)
                    .fold(
                        onSuccess = { state.loginRequestState.toSuccess(Unit) },
                        onFailure = { error -> state.loginRequestState.toError(error.message ?: "Unknown auth error", error) }
                    )
            )
        }
    }

    private suspend fun register(schema: RegisterSchema) {
        if (_uiState.value.loginRequestState.isFetching || _uiState.value.registerRequestState.isFetching) {
            this.logWarn { "Called register during fetch, ignoring" }
            return
        }

        _uiState.update { it.copy(registerRequestState = it.registerRequestState.toLoading()) }

        _uiState.update { state ->
            state.copy(
                registerRequestState = registerUseCase.invoke(schema).fold(
                    onSuccess = { state.registerRequestState.toSuccess(Unit) },
                    onFailure = { error -> state.registerRequestState.toError(error.message ?: "Unknown auth error", error) }
                )
            )
        }
    }

    fun onIntent(intent: AuthUIIntent) {
        when (intent) {
            is AuthUIIntent.InputLogin -> _uiState.update { it.copy(loginInputInfo = intent.input) }
            is AuthUIIntent.InputRegister -> _uiState.update { it.copy(registerInputInfo = intent.input) }
            is AuthUIIntent.SendLogin -> viewModelScope.launch { login(intent.schema) }
            is AuthUIIntent.SendRegister -> viewModelScope.launch { register(intent.schema) }
            is AuthUIIntent.SetAuthMode -> _uiState.update { it.copy(authMode = intent.authMode) }
        }
    }

    fun pageToAuthMode(page: Int): AuthMode {
        return when (page) {
            1 -> AuthMode.REGISTER
            else -> AuthMode.LOGIN
        }
    }

    fun authModeToPage(authMode: AuthMode): Int {
        return when (authMode) {
            AuthMode.LOGIN -> 0
            AuthMode.REGISTER -> 1
        }
    }
}
