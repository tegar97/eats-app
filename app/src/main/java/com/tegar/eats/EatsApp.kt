package com.tegar.eats

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalContext
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
import com.tegar.eats.ui.screen.ExploreScreen.ExploreScreen
import com.tegar.eats.ui.screen.detail.DetailRestaurant
import com.tegar.eats.ui.screen.home.HomeScreen
import com.tegar.eats.ui.screen.onboarding.OnboardingScreen
import com.tegar.eats.ui.screen.order.OrderScreen
import com.tegar.eats.ui.screen.profile.ProfileScreen
import com.tegar.eats.ui.screen.survey.SurveyScreen
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.ui.theme.Orange
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
            if (currentRoute != Screen.DetailRestaurant.route && currentRoute != Screen.OnBoarding.route && currentRoute != Screen.Survey.route) {
                TopBar()
            }
        },
        bottomBar = {
            if ( currentRoute != Screen.DetailRestaurant.route && currentRoute != Screen.OnBoarding.route && currentRoute != Screen.Survey.route) {
                if (currentRoute != null) {
                    BottomBar(navController, currentRoute)
                }
            }

        },
        modifier = modifier

    ) { paddingValue ->
        NavHost(
            navController = navController,
            startDestination = Screen.OnBoarding.route,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { restaurantId ->
                        navController.navigate(Screen.DetailRestaurant.createRoute(restaurantId))
                    },
                    navigateToSearch = { searchQuery ->
                        navController.navigate(Screen.Search.createRoute(searchQuery)) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(route = Screen.Search.route) { backStackEntry ->
                val searchQuery = backStackEntry.arguments?.getString("searchQuery") ?: ""
                ExploreScreen(
                    searchQuery = searchQuery,
                    navigateToDetail = { restaurantId ->
                        navController.navigate(Screen.DetailRestaurant.createRoute(restaurantId))
                    },

                    )

            }
            composable(Screen.OnBoarding.route) {
                OnboardingScreen(
                    navigateToSurvey = {
                        navController.navigate(Screen.Survey.route)
                    }
                )
            }
            composable(Screen.Survey.route) {
                SurveyScreen(
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailRestaurant.route,
                arguments = listOf(navArgument("restaurantId") { type = NavType.LongType }),
            ) {
                val restaurantId = it.arguments?.getLong("restaurantId") ?: -1L
                val context = LocalContext.current
                DetailRestaurant(
                    restaurantId = restaurantId,
                    navigateBack = { navController.navigateUp() },
                    onOrderButtonClicked = { message ->
                        shareOrder(context, message)
                    }
                )

            }
        }

    }


}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.eats_receipt))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.eats_receipt)
        )
    )
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
                icon =  if(currentRoute == Screen.Home.route  ) R.drawable.ic_home_filled else R.drawable.ic_home ,
                screen = Screen.Home

            ),
            NavigationItem(
                title = stringResource(R.string.menu_search),
                icon =  R.drawable.ic_search,
                screen = Screen.Search

            ),
            NavigationItem(
                title = stringResource(R.string.menu_order),
                icon = R.drawable.ic_shop_bag,
                screen = Screen.Order

            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = R.drawable.ic_profile,
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
                    Column(
                        horizontalAlignment =  Alignment.CenterHorizontally,
                        verticalArrangement =    Arrangement.spacedBy(10.dp)
                    ){

                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(23.5.dp),


                            )
                        if (currentRoute == item.screen.route) {
                            Divider(color = Orange, thickness = 2.38.dp, modifier = Modifier.width(23.dp).height(2.dp))
                        }else{
                            Divider(color = Color.Transparent, thickness = 2.38.dp, modifier = Modifier.width(23.dp).height(2.dp))

                        }

                    }
                },

                )
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

