package com.tegar.eats.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.di.Injection
import com.tegar.eats.ui.commons.UiState
import com.tegar.eats.ui.components.RestaurantItem
import com.tegar.eats.viewmodels.DetailRestaurantViewModel
import com.tegar.eats.viewmodels.ViewModelFactory
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun DetailRestaurant(
    restaurantId: Long,
    navigateBack: () -> Unit,
    viewModel: DetailRestaurantViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {

    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val cartState by viewModel.cartState.collectAsState(
        initial = UiState.Success(
            CartState(
                emptyList(),
                0
            )
        )
    )

    when (uiState) {
        is UiState.Loading -> {
            viewModel.getRestaurantById(restaurantId)
        }

        is UiState.Success -> {
            val data = (uiState as UiState.Success<Restaurant>).data
            val cartState = (cartState as UiState.Success<CartState>).data
            DetailContent(
                data.restaurantName,
                data.restaurantName,
                data.distance,
                data.estimateDistance,
                data.rating,
                data.ratingCount,
                data.foods,
                cartState.orderReward,
                cartState.totalPrice,
                isEmpty = { food ->
                    viewModel.shouldShowCartItem(food)

                },
                onAddToCart = { foodIndex, quantity ->
                    viewModel.addToCart(data.id, data.foods[foodIndex], quantity)
                },
                onRemoveFromCart = { cartItem ->
                    viewModel.removeFromCart(cartItem.restaurantId, cartItem.food)
                },
                onDecrementCartItem = { food ->
                    viewModel.decrementCartItem(food)
                },
                onClearCart = {
                    viewModel.clearCart()
                }
            )
        }

        is UiState.Error -> {
            // Handle error state
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    restaurantName: String,
    distance: String,
    estimatedDistance: String,
    rating: Double,
    ratingCount: Int,
    foods: List<Food>,
    cartItems: List<CartItem>,
    totalOrder : Int,
    isEmpty: (Food) -> Boolean,
    onAddToCart: (Int, Int) -> Unit,
    onRemoveFromCart: (CartItem) -> Unit,
    onDecrementCartItem: (Food) -> Unit,
    onClearCart: () -> Unit,

    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(cartItems.size.toString())
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column {
                Text(rating.toString())
            }
            Text(distance)
            Text(ratingCount.toString())
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(foods, key = { it.idFood }) { food ->

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Image(
                        painter = painterResource(id = food.foodImage),
                        contentDescription = null,
                        modifier = Modifier.size(72.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(food.foodName)
                        Text(food.foodPrice.toString())
                    }
                    Spacer(modifier = modifier.weight(1.0f))
                    if (!isEmpty(food)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                val foodIndex = foods.indexOf(food)

                                onAddToCart(foodIndex, 1)

                            }) {
                                Text("fasfa")
                            }
                        }


                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                if ((cartItems.find { it.food == food }?.quantity ?: 0) > 1) {
                                    onDecrementCartItem(food)
                                } else {
                                    onRemoveFromCart(cartItems.find { it.food == food }!!)
                                }
                            }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                            }
                            Text(
                                cartItems.find { it.food == food }?.quantity.toString(),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            IconButton(onClick = {
                                val foodIndex = foods.indexOf(food)
                                onAddToCart(foodIndex, 1)
                            }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }
                        }
                    }
                }

            }
        }
        Text(totalOrder.toString())

    }
}
