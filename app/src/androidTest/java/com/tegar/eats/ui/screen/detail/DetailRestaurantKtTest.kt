package com.tegar.eats.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.tegar.eats.R
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.utils.CurrencyFormatter
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailRestaurantKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeRestaurant = Restaurant(
        id = 1,
        restaurantImage = R.drawable.restaurant_image_1,
        restaurantName = "Majestic Delights",
        distance = "1.5 km",
        estimateDistance = "10 min",
        rating = 4.5,
        ratingCount = 120,
        foods = listOf<Food>(
            Food(
                idFood = 1,
                foodImage = R.drawable.food_1,
                foodName = "Pasta Carbonara",
                foodPrice = 1299,  // Representing the price in cents or smallest currency unit
                shortDescription = "Creamy pasta with bacon and cheese"
            ),
            Food(
                idFood = 2,
                foodImage = R.drawable.food_2,
                foodName = "Cream bacon",
                foodPrice = 999,
                shortDescription = "Creamy pasta with bacon and cheese"
            ),
            Food(
                idFood = 3,
                foodImage = R.drawable.food_3,
                foodName = "Grilled Salmon",
                foodPrice = 1899,
                shortDescription = "Grilled salmon with lemon and herbs"
            ),

            // Makanan keempat
            Food(
                idFood = 4,
                foodImage = R.drawable.food_4,
                foodName = "Chicken Alfredo",
                foodPrice = 1699,
                shortDescription = "Creamy chicken Alfredo pasta"
            ),
            Food(
                idFood = 5,
                foodImage = R.drawable.food_5,
                foodName = "Tiramisu",
                foodPrice = 899,
                shortDescription = "Classic Italian dessert with coffee flavor"
            ),

            ),
        address = "Jl. Majestic No. 1"
    )

    val FakeCartState = CartState(
        item  = listOf<CartItem>(
            CartItem(
                restaurantId = 1 ,
                food = fakeRestaurant.foods[0],
                quantity = 1
            )
        ),
        totalPrice = 5000
    )

    @Before
    fun setUp() {
        val formattedTotal = CurrencyFormatter.rupiahFormat.format(FakeCartState.totalPrice)
        composeTestRule.setContent {
            EatsTheme {
                DetailContent(
                    fakeRestaurant.restaurantName,
                    fakeRestaurant.address,
                    fakeRestaurant.distance,
                    fakeRestaurant.restaurantImage,
                    fakeRestaurant.rating,
                    fakeRestaurant.ratingCount,
                    fakeRestaurant.foods,
                    FakeCartState.item,
                    formattedTotal,
                    isEmpty = {false},
                    onAddToCart = { _, _ -> },
                    onRemoveFromCart  = {
                    },
                    onDecrementCartItem = { food ->

                    },
                    onBackClick = {},
                    onOrderButtonClicked = {}
                )
            }
        }
    }


    @Test
    fun detailRestaurant_isDisplayed() {
        composeTestRule.onNodeWithText(fakeRestaurant.restaurantName).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeRestaurant.address).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeRestaurant.distance).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeRestaurant.rating.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeRestaurant.ratingCount.toString()).assertIsDisplayed()

    }

    @Test
    fun check_if_restaurantProductExist() {
        composeTestRule.onNodeWithText(fakeRestaurant.foods[2].foodName).assertIsDisplayed()
        composeTestRule.onNodeWithText(CurrencyFormatter.rupiahFormat.format(fakeRestaurant.foods[2].foodPrice)).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeRestaurant.foods[2].shortDescription).assertIsDisplayed()


    }


}