package com.example.moviesjetpackcompose.modules.app.router

import cafe.adriel.voyager.core.screen.Screen

interface Router {

    fun navigateTo(screen: Screen)

    fun replaceTo(screen: Screen, isAll: Boolean = false)

    fun navigateBack()

    abstract class Abstract constructor() : Router {

        override fun navigateTo(screen: Screen) {
        }

        override fun replaceTo(screen: Screen, isAll: Boolean) {
        }

        override fun navigateBack() {
        }
    }

    class Base constructor() : Router, Abstract()
}

interface TabRouter {

    fun showTab(screen: Screen)

    class Base constructor(private val router: Router) : TabRouter {
        override fun showTab(screen: Screen) {
            router.replaceTo(screen = screen, isAll = true)
        }
    }
}
