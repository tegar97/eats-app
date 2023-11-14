package com.tegar.eats

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.tegar.eats.data.local.fake.FakeRestaurantDataSource
import com.tegar.eats.ui.navigation.Screen
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.utils.CurrencyFormatter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
class EatsAppKtTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    @Before
    fun setUp(){
        composeTestRule.setContent {
            EatsTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                EatsApp(navController = navController)
            }
        }
    }

    /*
        Ensure that the first time the application is opened it starts from the home route page
     */

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    /*
        Check if the bottom bar exists
    */
    @Test
    fun navHost_bottomNavigation_exist(){
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_search).performClick()
        navController.assertCurrentRouteName(Screen.Search.route)
        composeTestRule.onNodeWithStringId(R.string.menu_order).performClick()
        navController.assertCurrentRouteName(Screen.Order.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)

    }


    @Test
    fun navHost_clickItem_restaurantList1_navigateToDetailWithData(){
        composeTestRule.onNodeWithTag("restaurantsList").performScrollToIndex(3)
        composeTestRule.onNodeWithText(FakeRestaurantDataSource.dummyRestaurant[3].restaurantName).performClick()
        navController.assertCurrentRouteName(Screen.DetailRestaurant.route)
        composeTestRule.onNodeWithText(FakeRestaurantDataSource.dummyRestaurant[3].restaurantName).assertIsDisplayed()


    }

    @Test
    fun navHost_clickItem_restaurantList_AndBuySomeFood(){
        composeTestRule.onNodeWithTag("restaurantsList").performScrollToIndex(0)
        composeTestRule.onNodeWithText(FakeRestaurantDataSource.dummyRestaurant[0].restaurantName).performClick()
        navController.assertCurrentRouteName(Screen.DetailRestaurant.route)
        composeTestRule.onNodeWithText(FakeRestaurantDataSource.dummyRestaurant[0].restaurantName).assertIsDisplayed()
        // must turn off animation if want test this
        composeTestRule.onNodeWithTag("addBtn ${FakeRestaurantDataSource.dummyRestaurant[0].foods[0].idFood}").performClick()
        composeTestRule.onNodeWithTag("count ${FakeRestaurantDataSource.dummyRestaurant[0].foods[0].idFood}").assert(hasText("1"))
        composeTestRule.onNodeWithTag("totalPrice").assert(hasText("${CurrencyFormatter.rupiahFormat.format(FakeRestaurantDataSource.dummyRestaurant[0].foods[0].foodPrice)}"))

    }
    @Test
    fun exploreScreen_must_showlist_default(){
        composeTestRule.onNodeWithStringId(R.string.menu_search).performClick()
        navController.assertCurrentRouteName(Screen.Search.route)
        composeTestRule.onNodeWithTag("restaurantsList").assertIsDisplayed()
    }
    @Test
    fun exploreScreen_canSearch(){
        composeTestRule.onNodeWithStringId(R.string.menu_search).performClick()
        navController.assertCurrentRouteName(Screen.Search.route)
        composeTestRule.onNodeWithTag("search").performTextInput(FakeRestaurantDataSource.dummyRestaurant[0].restaurantName)

    }

    @Test
    fun navHost_searchItem_navigateToExploreWithData(){
        composeTestRule.onNodeWithTag("searchTextField").performTextInput(FakeRestaurantDataSource.dummyRestaurant[0].restaurantName)
        composeTestRule.onNodeWithTag("searchTextField").performImeAction()
        navController.assertCurrentRouteName(Screen.Search.route)

    }

    @Test
    fun navHost_searchItem_empty_mustStayInCurrentScreen(){
        composeTestRule.onNodeWithTag("searchTextField").performTextInput("")
        composeTestRule.onNodeWithTag("searchTextField").performImeAction()
        navController.assertCurrentRouteName(Screen.Home.route)
    }


    @Test
    fun navHost_clickitem_navigatesBack() {
        composeTestRule.onNodeWithTag("restaurantsList").performScrollToIndex(5)
        composeTestRule.onNodeWithText(FakeRestaurantDataSource.dummyRestaurant[5].restaurantName).performClick()
        navController.assertCurrentRouteName(Screen.DetailRestaurant.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun check_category() {
        composeTestRule.onNodeWithStringId(R.string.near_category).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.promo_category).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.healty_category).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.best_seller_category).assertIsDisplayed()


    }


}