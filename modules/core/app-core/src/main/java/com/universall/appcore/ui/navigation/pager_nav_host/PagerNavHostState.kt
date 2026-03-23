package com.universall.appcore.ui.navigation.pager_nav_host

import androidx.compose.foundation.pager.PagerState

class PagerNavHostState<T : PagerNavRoute> internal constructor(
    private val pagerState: PagerState,
    internal val routes: List<T>
) : PagerNavController<T> {
    private var navigating = false

    override val currentRouteIndex: Int get() = pagerState.currentPage
    override val currentRoute: T get() = routes[pagerState.currentPage]

    override fun getRoutePageByName(name: String): Int {
        return routes.indexOfFirst { it.name == name }.takeIf { it >= 0 } ?: throw IllegalStateException("Route with name $name is not registered in this pager nav host")
    }

    override fun getRouteByName(name: String): T {
        return routes.firstOrNull { it.name == name } ?: throw IllegalStateException("Route with name $name is not registered in this pager nav host")
    }

    override fun getRouteByPage(page: Int): T {
        return routes[page]
    }

    override fun getPageForRoute(route: T): Int {
        return routes.indexOfFirst { it == route }.takeIf { it >= 0 } ?: throw IllegalStateException("Route with name '${route.name}' is not declared in this pager nav host")
    }

    override suspend fun navigateTo(route: T) {
        navigateTo(getPageForRoute(route))
    }

    override suspend fun navigateTo(route: String) {
        navigateTo(getRoutePageByName(route))
    }

    override suspend fun navigateTo(page: Int) {
        if (navigating) return

        try {
            navigating = true
            pagerState.animateScrollToPage(page)
        } finally {
            navigating = false

        }
    }

    internal fun asPagerState(): PagerState = pagerState
}
