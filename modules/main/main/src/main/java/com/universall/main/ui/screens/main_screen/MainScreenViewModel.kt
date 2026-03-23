package com.universall.main.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universall.main.ui.navigation.MainScreenPagerNavRoute
import com.universall.main.ui.screens.main_screen.MainScreenUIEffect.PagerNavigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<MainScreenUIEffect>()
    val effects = _effects.asSharedFlow()

    fun onIntent(intent: MainScreenUIIntent) {
        when (intent) {
            MainScreenUIIntent.NavigateToHome -> viewModelScope.launch { _effects.emit(PagerNavigate(destination = MainScreenPagerNavRoute.HomePageNavRoute)) }
            MainScreenUIIntent.NavigateToProfile -> viewModelScope.launch { _effects.emit(PagerNavigate(destination = MainScreenPagerNavRoute.ProfilePageNavRoute)) }
        }
    }
}
