package com.universall.appcore.ui.navigation.pager_nav_host

class PagerNavHostScope<T : PagerNavRoute> internal constructor(
    val pagerNavController: PagerNavController<T>
) {
    val currentRoute: PagerNavRoute get() = pagerNavController.currentRoute
}
