package com.tegar.eats.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Notification : Screen("notification")
    object Order : Screen("order")
    object Profile : Screen("profile")

    object DetailRestaurant : Screen("home/{resId}") {
        fun createRoute(resId: Long) = "home/$resId"
    }

}