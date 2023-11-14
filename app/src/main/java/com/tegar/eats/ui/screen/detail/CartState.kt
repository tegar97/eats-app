package com.tegar.eats.ui.screen.detail

import com.tegar.eats.data.local.model.CartItem

data class CartState(
    val item: List<CartItem>,
    val totalPrice: Int
)