package com.tegar.eats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tegar.eats.ui.navigation.NavigationItem
import com.tegar.eats.ui.navigation.Screen
import com.tegar.eats.ui.screen.detail.DetailRestaurant
import com.tegar.eats.ui.screen.home.HomeScreen
import com.tegar.eats.ui.screen.notification.NotificationScreen
import com.tegar.eats.ui.screen.order.OrderScreen
import com.tegar.eats.ui.screen.profile.ProfileScreen
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.utils.LocalCustomColorsPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()

) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {

            if (currentRoute != null) {
                BottomBar(navController, currentRoute)
            }

        },
        modifier = modifier

    ) { paddingValue ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail =  {restaurantId ->
                        navController.navigate(Screen.DetailRestaurant.createRoute(restaurantId))

                    }
                )
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
            composable(
                route = Screen.DetailRestaurant.route,
                arguments = listOf(navArgument("restaurantId") { type = NavType.LongType }),


                ){
                val restaurantId = it.arguments?.getLong("restaurantId") ?: -1L
                    DetailRestaurant(restaurantId =  restaurantId ,                     navigateBack = { navController.navigateUp() },
                    )

            }
        }

    }


}

@Composable
private fun BottomBar(
    navController: NavHostController,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        tonalElevation = 1.dp,
        containerColor = LocalCustomColorsPalette.current.costumeCardColor,
        modifier = modifier
    ) {

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = if (currentRoute == Screen.Home.route) Icons.Filled.Home else Icons.Outlined.Home,
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

            NavigationBarItem(


                selected = currentRoute == item.screen.route,
                onClick = {
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
                        contentDescription = item.title,

                        )
                })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Column {
                Text(
                    stringResource(id = R.string.your_location_text),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = LocalCustomColorsPalette.current.costumeRegularbodyText
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_marker),
                        contentDescription = null,
                        Modifier
                            .width(13.dp)
                            .height(15.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.dummy_location),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = LocalCustomColorsPalette.current.costumeRegularTitleText
                        )
                    )
                }
            }
        },
        actions = {
            Image(
                painter = painterResource(R.drawable.icon_fav),
                colorFilter = ColorFilter.tint(LocalCustomColorsPalette.current.costumeWhiteBlack),
                contentDescription = null,
                modifier = Modifier.size(24.dp)

            )
        },
        modifier = modifier

    )
}

@Preview
@Composable
fun EatsAppPreview() {
    EatsTheme {
        EatsApp()
    }
}

//@Preview
//@Composable
//fun TopAppBarPreview() {
//    EatsTheme {
//        TopBar()
//    }
//}