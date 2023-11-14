package com.tegar.eats.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food


@Composable
fun ButtonQuantity(
    foods: List<Food>,
    food: Food,

    cartItems: List<CartItem>,
    onAddToCart: (Int, Int) -> Unit,
    onRemoveFromCart: (CartItem) -> Unit,
    onDecrementCartItem: (Food) -> Unit
) {
    Log.d("food.idFood" , food.idFood.toString())
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if ((cartItems.find { it.food == food }?.quantity ?: 0) > 1) {
                onDecrementCartItem(food)
            } else {
                onRemoveFromCart(cartItems.find { it.food == food }!!)
            }

        }, modifier = Modifier.testTag("decreaseBtn ${food.idFood}")) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
        }
        Text(
            cartItems.find { it.food == food }?.quantity.toString(),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .testTag("count ${food.idFood}")
        )
        IconButton(onClick = {
            val foodIndex = foods.indexOf(food)
            onAddToCart(foodIndex, 1)
        }, modifier = Modifier.testTag("increaseBtn ${food.idFood}")) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}