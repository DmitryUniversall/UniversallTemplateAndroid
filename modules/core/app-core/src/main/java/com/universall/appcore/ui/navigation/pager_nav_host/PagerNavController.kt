package com.universall.appcore.ui.navigation.pager_nav_host

interface PagerNavController<T : PagerNavRoute> {
    val currentRouteIndex: Int
    val currentRoute: T

    fun getRoutePageByName(name: String): Int
    fun getRouteByName(name: String): T
    fun getRouteByPage(page: Int): T
    fun getPageForRoute(route: T): Int

    suspend fun navigateTo(route: T)
    suspend fun navigateTo(route: String)
    suspend fun navigateTo(page: Int)
}
