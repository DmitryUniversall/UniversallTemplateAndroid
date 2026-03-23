package com.universall.main.ui.screens.main_screen

internal data class MainScreenUIState(
    val forTemplate: Int = 0
) {
    companion object {
        fun empty(): MainScreenUIState = MainScreenUIState()
    }
}
