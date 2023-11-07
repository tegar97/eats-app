package com.tegar.eats.data

import com.tegar.eats.R
import com.tegar.eats.model.Restaurant

val dummyRestaurantList = listOf(
    Restaurant(
        restaurantImage = R.drawable.restaurant_image_1,
        restaurantName = "Majestic Delights",
        distance = "1.5 km",
        estimateDistance = "10 min",
        rating = 4.5,
        ratingCount = 120
    ),
    Restaurant(
        restaurantImage = R.drawable.restaurant_image_2,
        restaurantName = "Grand Feast",
        distance = "2.2 km",
        estimateDistance = "15 min",
        rating = 4.2,
        ratingCount = 90
    ),
    Restaurant(
        restaurantImage = R.drawable.restaurant_image_3,
        restaurantName = "Royal Bites",
        distance = "0.8 km",
        estimateDistance = "5 min",
        rating = 4.8,
        ratingCount = 150
    ),
    Restaurant(
        restaurantImage = R.drawable.restaurant_image_4,
        restaurantName = "Palatial Plates",
        distance = "3.5 km",
        estimateDistance = "20 min",
        rating = 4.0,
        ratingCount = 80
    ),

)

val popularList = dummyRestaurantList.shuffled()

