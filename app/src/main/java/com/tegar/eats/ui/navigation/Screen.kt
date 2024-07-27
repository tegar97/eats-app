package com.tegar.eats.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object OnBoarding : Screen("onboarding")
    object Survey : Screen("survey")

    object Search : Screen("search/{searchQuery}") {
        fun createRoute(searchQuery: String) = "search/$searchQuery"
    }
    object Order : Screen("order")
    object Profile : Screen("profile")

    object DetailRestaurant : Screen("home/{restaurantId}") {
        fun createRoute(resId: Long) = "home/$resId"
    }

}