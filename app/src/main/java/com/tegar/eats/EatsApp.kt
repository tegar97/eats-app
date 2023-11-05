package com.tegar.eats

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tegar.eats.ui.navigation.NavigationItem
import com.tegar.eats.ui.navigation.Screen
import com.tegar.eats.ui.screen.home.HomeScreen
import com.tegar.eats.ui.screen.notification.NotificationScreen
import com.tegar.eats.ui.screen.order.OrderScreen
import com.tegar.eats.ui.screen.profile.ProfileScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()

) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {

            BottomBar(navController)

        },
        modifier = modifier

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Notification.route) {
                NotificationScreen()
            }
            composable(Screen.Order.route) {
                OrderScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }

    }


}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home

            ),
            NavigationItem(
                title = stringResource(R.string.menu_notification),
                icon = Icons.Default.Notifications,
                screen = Screen.Notification

            ),
            NavigationItem(
                title = stringResource(R.string.menu_order),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Order

            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )

        navigationItems.map { item ->
            NavigationBarItem(selected = false, onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }

            },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                })
        }
    }
}