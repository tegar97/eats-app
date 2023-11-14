package com.tegar.eats.data.local.fake

import com.tegar.eats.R
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant

object FakeRestaurantDataSource {
    private val dummyFood = listOf<Food>(


        // Makanan kelima

        // Makanan keenam

        // Makanan kesembilan

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
        ),

        Restaurant(
            id = 2,
            restaurantImage = R.drawable.restaurant_image_2,
            restaurantName = "Grand Feast",
            distance = "2.2 km",
            estimateDistance = "15 min",
            rating = 4.2,
            ratingCount = 90,
            foods = listOf<Food>(
                Food(
                    idFood = 6,
                    foodImage = R.drawable.food_6,
                    foodName = "Sushi Platter",
                    foodPrice = 2499,
                    shortDescription = "Assorted sushi on a platter"
                ),

                // Makanan ketujuh
                Food(
                    idFood = 7,
                    foodImage = R.drawable.food_7,
                    foodName = "Burger Deluxe",
                    foodPrice = 1399,
                    shortDescription = "Deluxe burger with beef patty and special sauce"
                ),

                // Makanan kedelapan
                Food(
                    idFood = 8,
                    foodImage = R.drawable.food_8,
                    foodName = "Caprese Salad",
                    foodPrice = 1099,
                    shortDescription = "Fresh tomato, mozzarella, and basil salad"
                ),
            ),
            address = "Jl. Grand No. 2"
        ),

        Restaurant(
            id = 3,
            restaurantImage = R.drawable.restaurant_image_3,
            restaurantName = "Royal Bites",
            distance = "0.8 km",
            estimateDistance = "5 min",
            rating = 4.8,
            ratingCount = 150,
            foods = listOf<Food>(
                Food(
                    idFood = 11,
                    foodImage = R.drawable.food_11,
                    foodName = "Truffle Risotto",
                    foodPrice = 189000,  // Representing the price in Rupiah
                    shortDescription = "Creamy risotto infused with truffle oil"
                ),

                Food(
                    idFood = 12,
                    foodImage = R.drawable.food_12,
                    foodName = "Lobster Thermidor",
                    foodPrice = 289000,
                    shortDescription = "Lobster cooked with a creamy brandy-infused sauce"
                ),

                Food(
                    idFood = 13,
                    foodImage = R.drawable.food_13,
                    foodName = "Wagyu Beef Steak",
                    foodPrice = 459000,
                    shortDescription = "Juicy Wagyu beef steak cooked to perfection"
                ),
                Food(
                    idFood = 14,
                    foodImage = R.drawable.food_5,
                    foodName = "Foie Gras Sushi",
                    foodPrice = 369000,
                    shortDescription = "Sushi topped with luxurious foie gras"
                ),
            ),
            address = "Jl. Royal No. 3"
        ),

        Restaurant(
            id = 4,
            restaurantImage = R.drawable.restaurant_image_4,
            restaurantName = "Palatial Plates",
            distance = "3.5 km",
            estimateDistance = "20 min",
            rating = 4.0,
            ratingCount = 80,
            foods = listOf<Food>(
                Food(
                    idFood = 15,
                    foodImage = R.drawable.food_5,
                    foodName = "Gold Leaf Dessert",
                    foodPrice = 189000,
                    shortDescription = "Decadent dessert adorned with edible gold leaf"
                ),
                Food(
                    idFood = 16,
                    foodImage = R.drawable.food_4,
                    foodName = "Caviar Blini",
                    foodPrice = 289000,
                    shortDescription = "Blinis topped with luxurious caviar"
                ),
                Food(
                    idFood = 17,
                    foodImage = R.drawable.food_11,
                    foodName = "Alaskan King Crab Legs",
                    foodPrice = 389000,
                    shortDescription = "Fresh Alaskan king crab legs served with butter"
                ),
                Food(
                    idFood = 18,
                    foodImage = R.drawable.food_8,
                    foodName = "Black Truffle Pizza",
                    foodPrice = 359000,
                    shortDescription = "Pizza topped with black truffle slices"
                ),
                Food(
                    idFood = 19,
                    foodImage = R.drawable.food_2,
                    foodName = "Oysters Rockefeller",
                    foodPrice = 329000,
                    shortDescription = "Oysters baked with a rich spinach and cheese sauce"
                ),
            ),
            address = "Jl. Palatial No. 4"
        ),

        Restaurant(
            id = 5,
            restaurantImage = R.drawable.restaurant_image_5,
            restaurantName = "Savor Street",
            distance = "2.0 km",
            estimateDistance = "12 min",
            rating = 4.3,
            ratingCount = 110,
            foods = listOf<Food>(
                Food(
                    idFood = 20,
                    foodImage = R.drawable.food_13,
                    foodName = "Lobster Bisque",
                    foodPrice = 259000,
                    shortDescription = "Creamy soup made with lobster meat"
                ),
                Food(
                    idFood = 21,
                    foodImage = R.drawable.food_1,
                    foodName = "Crispy Duck Confit",
                    foodPrice = 389000,
                    shortDescription = "Duck leg slow-cooked until crispy"
                ),
                Food(
                    idFood = 22,
                    foodImage = R.drawable.food_2,
                    foodName = "Prime Rib Steak",
                    foodPrice = 429000,
                    shortDescription = "Juicy prime rib steak seasoned and grilled to perfection"
                ),
                Food(
                    idFood = 23,
                    foodImage = R.drawable.food_4,
                    foodName = "Scallops with Champagne Sauce",
                    foodPrice = 349000,
                    shortDescription = "Scallops served in a rich champagne-infused sauce"
                ),
                Food(
                    idFood = 24,
                    foodImage = R.drawable.food_2,
                    foodName = "Chocolate Soufflé",
                    foodPrice = 199000,
                    shortDescription = "Decadent chocolate soufflé with a molten center"
                ),
            ),
            address = "Jl. Savor No. 5"
        ),

        Restaurant(
            id = 6,
            restaurantImage = R.drawable.restaurant_image_6,
            restaurantName = "Gourmet Garden",
            distance = "1.0 km",
            estimateDistance = "8 min",
            rating = 4.6,
            ratingCount = 130,
            foods = listOf<Food> (
                Food(
                    idFood = 25,
                    foodImage = R.drawable.food_11,
                    foodName = "Braised Lamb Shank",
                    foodPrice = 369000,
                    shortDescription = "Lamb shank slow-cooked in a flavorful broth"
                ),
                Food(
                    idFood = 26,
                    foodImage = R.drawable.food_12,
                    foodName = "Saffron Risotto",
                    foodPrice = 309000,
                    shortDescription = "Risotto infused with the delicate flavor of saffron"
                ),
                Food(
                    idFood = 27,
                    foodImage = R.drawable.food_13,
                    foodName = "Foie Gras Terrine",
                    foodPrice = 429000,
                    shortDescription = "Terrine made with rich foie gras"
                ),
            ),
            address = "Jl. Gourmet No. 6"
        ),

        Restaurant(
            id = 7,
            restaurantImage = R.drawable.restaurant_image_7,
            restaurantName = "Epicurean Eats",
            distance = "4.0 km",
            estimateDistance = "25 min",
            rating = 4.1,
            ratingCount = 95,
            foods = listOf<Food> (
                Food(
                    idFood = 28,
                    foodImage = R.drawable.food_2,
                    foodName = "Seafood Platter",
                    foodPrice = 449000,
                    shortDescription = "Assorted seafood on a platter with dipping sauces"
                ),
                Food(
                    idFood = 29,
                    foodImage = R.drawable.food_3,
                    foodName = "Mushroom Truffle Soup",
                    foodPrice = 259000,
                    shortDescription = "Creamy mushroom soup with a truffle twist"
                ),
            ),

            address = "Jl. Epicurean No. 7"
        ),

        Restaurant(
            id = 8,
            restaurantImage = R.drawable.restaurant_image_8,
            restaurantName = "Taste Temptations",
            distance = "1.8 km",
            estimateDistance = "13 min",
            rating = 4.4,
            ratingCount = 125,
            foods =  listOf<Food> (
                Food(
                    idFood = 30,
                    foodImage = R.drawable.food_12,
                    foodName = "Tuna Tartare",
                    foodPrice = 369000,
                    shortDescription = "Fresh tuna diced and seasoned with herbs"
                ),

                // Makanan ke-31 sampai ke-40
                Food(
                    idFood = 31,
                    foodImage = R.drawable.food_8,
                    foodName = "Beef Wellington",
                    foodPrice = 499000,
                    shortDescription = "Beef fillet coated with mushroom duxelles and wrapped in puff pastry"
                ),
                Food(
                    idFood = 32,
                    foodImage = R.drawable.food_2,
                    foodName = "Lobster Roll",
                    foodPrice = 389000,
                    shortDescription = "Roll filled with succulent lobster meat and mayo"
                ),
            )

            ,
            address = "Jl. Taste No. 8"
        ),

        Restaurant(
            id = 9,
            restaurantImage = R.drawable.restaurant_image_9,
            restaurantName = "Flavorful Fusion",
            distance = "3.2 km",
            estimateDistance = "18 min",
            rating = 4.7,
            ratingCount = 140,
            foods =  listOf<Food> (
                Food(
                    idFood = 31,
                    foodImage = R.drawable.food_13,
                    foodName = "Beef Wellington",
                    foodPrice = 499000,
                    shortDescription = "Beef fillet coated with mushroom duxelles and wrapped in puff pastry"
                ),
                Food(
                    idFood = 32,
                    foodImage = R.drawable.restaurant_image_10,
                    foodName = "Lobster Roll",
                    foodPrice = 389000,
                    shortDescription = "Roll filled with succulent lobster meat and mayo"
                ),
                Food(
                    idFood = 33,
                    foodImage = R.drawable.restaurant_image_5,
                    foodName = "Duck Foie Gras",
                    foodPrice = 599000,
                    shortDescription = "Pan-seared duck foie gras served with balsamic reduction"
                ),
                Food(
                    idFood = 34,
                    foodImage = R.drawable.restaurant_image_2,
                    foodName = "Sushi Omakase",
                    foodPrice = 449000,
                    shortDescription = "Chef's selection of premium sushi"
                ),
                Food(
                    idFood = 35,
                    foodImage = R.drawable.restaurant_image_1,
                    foodName = "Truffle Mac and Cheese",
                    foodPrice = 289000,
                    shortDescription = "Macaroni and cheese with a generous serving of truffle"
                ),
            )
            ,
            address = "Jl. Flavorful No. 9"
        ),

        Restaurant(
            id = 10,
            restaurantImage = R.drawable.restaurant_image_10,
            restaurantName = "Culinary Corner",
            distance = "0.5 km",
            estimateDistance = "4 min",
            rating = 4.9,
            ratingCount = 160,
            foods = listOf<Food>(),
            address = "Jl. Culinary No. 10"
        ),

        Restaurant(
            id = 11,
            restaurantImage = R.drawable.restaurant_image_11,
            restaurantName = "Exquisite Eateries",
            distance = "2.5 km",
            estimateDistance = "16 min",
            rating = 4.2,
            ratingCount = 100,
            foods = listOf<Food>(),
            address = "Jl. Exquisite No. 11"
        ),

        Restaurant(
            id = 12,
            restaurantImage = R.drawable.restaurant_image_12,
            restaurantName = "Delicious Destinations",
            distance = "1.2 km",
            estimateDistance = "9 min",
            rating = 4.6,
            ratingCount = 135,
            foods = listOf<Food>(),
            address = "Jl. Delicious No. 12"
        )
    )


}


