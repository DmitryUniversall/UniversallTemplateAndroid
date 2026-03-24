package com.universall.init_impl.ui.screens.init_screen

import com.universall.appcore.ui.resources.ResourceState

internal data class InitScreenUIState(
    val restoreAuthRequestState: ResourceState<Unit>
) {
    companion object {
        fun empty(): InitScreenUIState = InitScreenUIState(
            restoreAuthRequestState = ResourceState.Idle
        )
    }
}
