package com.tegar.eats.data.local.repository

import com.tegar.eats.data.local.fake.FakeRestaurantDataSource
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.ui.screen.detail.CartState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class RestaurantRepository {
    private val restaurants = mutableListOf<Restaurant>()


    private val cartItems = mutableListOf<CartItem>()
    private val _cart = MutableStateFlow<List<CartItem>>(cartItems)

    init {
        if (restaurants.isEmpty()) {
            FakeRestaurantDataSource.dummyRestaurant.forEach { restaurant ->
                restaurants.add(
                    Restaurant(
                        restaurant.id,
                        restaurant.restaurantImage,
                        restaurant.restaurantName,
                        restaurant.distance,
                        restaurant.estimateDistance,
                        restaurant.rating,
                        restaurant.ratingCount,

                        restaurant.foods,
                        restaurant.address,
                    )
                )
            }
        }
    }

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return flowOf(restaurants)
    }

    fun decrementCartItem(food: Food) {
        val existingItem = cartItems.find { it.food == food }

        if (existingItem != null) {
            existingItem.quantity -= 1

            if (existingItem.quantity <= 0) {
                cartItems.remove(existingItem)
            }

            _cart.value = cartItems
        }
    }

    fun getRestaurantById(restaurantId: Long): Flow<Restaurant> {
        return flow {
            // Just to simulate shimmer stil work
//            delay(500)
            emit(restaurants.first { it.id == restaurantId })
        }
    }

    fun searchRestaurants(query: String): Flow<List<Restaurant>> {
        return flow {
            delay(100)
            emit(filterRestaurants(query))
        }
    }

    private fun filterRestaurants(query: String): List<Restaurant> {
        return if (query.isBlank()) {
            restaurants
        } else {
            restaurants.filter { restaurant ->
                restaurant.restaurantName.contains(query, ignoreCase = true) ||
                        restaurant.foods.any { food ->
                            food.foodName.contains(
                                query,
                                ignoreCase = true
                            )
                        }
            }
        }
    }

    fun addToCart(restaurantId: Long, food: Food, quantity: Int) {
        val existingItem = cartItems.find { it.restaurantId == restaurantId && it.food == food }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            cartItems.add(CartItem(restaurantId, food, quantity))
        }

        _cart.value = cartItems
    }


    fun getCartItem(food: Food): CartItem? {
        return cartItems.find { it.food == food }
    }

    fun removeFromCart(restaurantId: Long, food: Food) {
        val existingItem = cartItems.find { it.restaurantId == restaurantId && it.food == food }
        if (existingItem != null) {
            cartItems.remove(existingItem)
            _cart.value = cartItems
        }
    }


    fun getCartState(): CartState {
        val totalPrice = cartItems.sumBy { it.food.foodPrice * it.quantity }
        return CartState(cartItems, totalPrice)
    }


    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null
        fun getInstance(): RestaurantRepository =
            instance ?: synchronized(this) {
                RestaurantRepository().apply {
                    instance = this
                }
            }

    }

}