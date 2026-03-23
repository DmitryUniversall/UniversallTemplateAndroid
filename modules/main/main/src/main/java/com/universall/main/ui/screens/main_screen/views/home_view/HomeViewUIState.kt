package com.universall.main.ui.screens.main_screen.views.home_view

internal data class HomeViewUIState(
    val forTemplate: Int = 0
) {
    companion object {
        fun empty(): HomeViewUIState = HomeViewUIState()
    }
}
