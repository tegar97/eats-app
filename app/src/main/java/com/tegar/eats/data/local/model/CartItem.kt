package com.tegar.eats.data.local.model

data class CartItem(
    val restaurantId: Long,
    val food: Food,
    var quantity: Int
)