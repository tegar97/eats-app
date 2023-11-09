package com.tegar.eats.data.local.fake

import com.tegar.eats.R
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant

object FakeRestaurantDataSource {
    private val dummyFood = listOf<Food>(
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
            foodImage = R.drawable.food_2,
            foodName = "Cream sadasdbacon",
            foodPrice = 999,
            shortDescription = "Creamy pasta with bacon and cheese"
        ),
        )
    val dummyRestaurant = listOf(
        Restaurant(
            id = 1,
            restaurantImage = R.drawable.restaurant_image_1,
            restaurantName = "Majestic Delights",
            distance = "1.5 km",
            estimateDistance = "10 min",
            rating = 4.5,
            ratingCount = 120,
            foods = dummyFood.shuffled()


        ),
        Restaurant(
            id = 2,
            restaurantImage = R.drawable.restaurant_image_2,
            restaurantName = "Grand Feast",
            distance = "2.2 km",
            estimateDistance = "15 min",
            rating = 4.2,
            ratingCount = 90,
            foods = dummyFood.shuffled()

        ),
        Restaurant(
            id = 3,
            restaurantImage = R.drawable.restaurant_image_3,
            restaurantName = "Royal Bites",
            distance = "0.8 km",
            estimateDistance = "5 min",
            rating = 4.8,
            ratingCount = 150,
            foods = dummyFood.shuffled()

        ),
        Restaurant(
            id = 4,
            restaurantImage = R.drawable.restaurant_image_4,
            restaurantName = "Palatial Plates",
            distance = "3.5 km",
            estimateDistance = "20 min",
            rating = 4.0,
            ratingCount = 80,
            foods = dummyFood
        ),

        )


}


