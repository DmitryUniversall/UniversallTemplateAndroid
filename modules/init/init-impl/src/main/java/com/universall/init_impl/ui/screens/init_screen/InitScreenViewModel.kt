package com.universall.init_impl.ui.screens.init_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.navigation_impl.destinations.auth.AuthDestination
import com.universall.appcore.appcore.ui.state.isFetching
import com.universall.appcore.appcore.ui.state.toError
import com.universall.appcore.appcore.ui.state.toLoading
import com.universall.appcore.appcore.ui.state.toRefreshing
import com.universall.appcore.appcore.ui.state.toSuccess
import com.universall.appcore.appcore.ui.utils.UIString
import com.universall.appcore.appcore.logs.logError
import com.universall.appcore.appcore.logs.logInfo
import com.universall.appcore.appcore.logs.logWarn
import com.universall.auth_api.domain.entities.AuthState
import com.universall.auth_api.domain.usecases.LocalLogoutUseCase
import com.universall.auth_api.domain.usecases.RestoreAuthStateUseCase
import com.universall.core.utils.messageOrDefault
import com.universall.init_impl.R
import com.universall.navigation_impl.destinations.init.InitDestination
import com.universall.navigation_impl.destinations.main.MainScreenDestination
import com.universall.server_tools_api.domain.usecases.PingServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class InitScreenViewModel @Inject constructor(
    private val restoreAuthStateUseCase: RestoreAuthStateUseCase,
    private val localLogoutUseCase: LocalLogoutUseCase,
    private val pingServerUseCase: PingServerUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(InitScreenUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<InitScreenUIEffect>()
    val effects = _effects.asSharedFlow()

    private suspend fun restoreAuthState(refresh: Boolean = false) {
        if (_uiState.value.restoreAuthRequestState.isFetching) {  // TODO: Make mutex
            this.logWarn { "Called restoreAuthState during fetch, ignoring" }
        }

        _uiState.update { it.copy(restoreAuthRequestState = if (refresh) it.restoreAuthRequestState.toRefreshing() else it.restoreAuthRequestState.toLoading()) }

        pingServerUseCase.invoke().exceptionOrNull()?.let { error ->
            this.logError(error) { "Unexpected error occurred: Server unreachable" }

            _uiState.update { state ->
                state.copy(
                    restoreAuthRequestState = state.restoreAuthRequestState.toError(
                        UIString.of(error.messageOrDefault(""))
                    )
                )
            }

            return
        }

        val restoreAuthStateResult = restoreAuthStateUseCase.invoke()

        val newRestoreState = restoreAuthStateResult.fold(
            onSuccess = { authState ->
                this.logInfo { "Restored auth state: ${authState::class.simpleName}" }

                when (authState) {
                    is AuthState.Authenticated -> {
                        _effects.emit(InitScreenUIEffect.Navigate(MainScreenDestination, popUpTo = InitDestination, inclusive = true))
                        _uiState.value.restoreAuthRequestState.toSuccess(Unit)
                    }
                    is AuthState.Unauthenticated -> {
                        _effects.emit(InitScreenUIEffect.Navigate(AuthDestination, popUpTo = InitDestination, inclusive = true))
                        _uiState.value.restoreAuthRequestState.toSuccess(Unit)
                    }
                    is AuthState.TemporarilyUnauthenticated -> {
                        _uiState.value.restoreAuthRequestState.toError(UIString.of(authState.reason))
                    }
                    is AuthState.Unknown -> {
                        _uiState.value.restoreAuthRequestState.toError(UIString.resource(R.string.unknown_error))
                    }
                }
            },
            onFailure = { error ->
                this.logError(error) { "Unexpected error occurred" }

                _uiState.value.restoreAuthRequestState.toError(
                    errorMessage = error.message?.let { UIString.of(it) } ?: UIString.resource(R.string.unknown_error)
                )
            }
        )

        _uiState.update { it.copy(restoreAuthRequestState = newRestoreState) }
    }

    private suspend fun localLogout() {
        localLogoutUseCase.invoke()
        _effects.emit(InitScreenUIEffect.Navigate(AuthDestination, popUpTo = InitDestination, inclusive = true))
    }

    fun onIntent(intent: InitScreenUIIntent) {
        when (intent) {
            InitScreenUIIntent.RestoreAuthState -> viewModelScope.launch { restoreAuthState() }
            InitScreenUIIntent.Refresh -> viewModelScope.launch { restoreAuthState(refresh = true) }
            InitScreenUIIntent.LocalLogout -> viewModelScope.launch { localLogout() }
        }
    }
}
