package com.universall.appcore.ui.navigation.pager_nav_host

import androidx.compose.runtime.Composable

class PagerNavHostResolver<T : PagerNavRoute> internal constructor(
    private val state: PagerNavHostState<T>,
    private val routes: Map<T, @Composable PagerNavHostScope<T>.() -> Unit>
) {
    class Builder<T : PagerNavRoute> internal constructor(
        private val state: PagerNavHostState<T>
    ) {
        internal val routes: MutableMap<T, (@Composable PagerNavHostScope<T>.() -> Unit)?> = state.routes.associateWith { null }.toMutableMap()

        fun composable(route: T, content: @Composable PagerNavHostScope<T>.() -> Unit) {
            routes[route] = content
        }

        internal fun build(): PagerNavHostResolver<T> = PagerNavHostResolver(
            routes = routes.mapValues { (route, composable) -> requireNotNull(composable) { "Route '${route.name}' was declared but never used" } }.toMap(),
            state = state
        )
    }

    @Composable
    fun DrawContent(route: T) {
        val scope = PagerNavHostScope(pagerNavController = state)
        val composable = requireNotNull(routes[route]) { "Composable for route '${route.name}' was not found" }
        composable.invoke(scope)
    }
}