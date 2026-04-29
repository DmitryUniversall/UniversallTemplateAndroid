package com.universall.appcore.appcore.ui.pager_nav_host

class PagerNavHostScope<T : PagerNavRoute> internal constructor(
    val pagerNavController: PagerNavController<T>
) {
    val currentRoute: PagerNavRoute get() = pagerNavController.currentRoute
}
