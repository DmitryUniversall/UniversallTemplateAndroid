package com.universall.main.ui.navigation

import com.universall.appcore.ui.navigation.pager_nav_host.PagerNavRoute

internal sealed interface MainScreenPagerNavRoute : PagerNavRoute {
    object HomePageNavRoute : MainScreenPagerNavRoute {
        override val name: String = "home"
    }

    object ProfilePageNavRoute : MainScreenPagerNavRoute {
        override val name: String = "profile"
    }

    companion object {
        val routes: List<MainScreenPagerNavRoute> = listOf(HomePageNavRoute, ProfilePageNavRoute)
    }
}
