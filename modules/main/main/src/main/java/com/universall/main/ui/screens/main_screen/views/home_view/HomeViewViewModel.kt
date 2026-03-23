package com.universall.main.ui.screens.main_screen.views.home_view
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class HomeViewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeViewUIState.empty())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<HomeViewUIEffect>()
    val effects = _effects.asSharedFlow()

    fun onIntent(intent: HomeViewUIIntent) {

    }
}
