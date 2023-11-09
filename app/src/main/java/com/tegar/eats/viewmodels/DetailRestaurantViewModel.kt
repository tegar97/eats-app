package com.tegar.eats.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.data.local.repository.RestaurantRepository
import com.tegar.eats.ui.commons.UiState
import com.tegar.eats.ui.screen.detail.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailRestaurantViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Restaurant>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Restaurant>>
        get() = _uiState

    private val _cartState: MutableStateFlow<UiState<CartState>> =
        MutableStateFlow(UiState.Success(CartState(emptyList(), 0)))
    val cartState: StateFlow<UiState<CartState>>
        get() = _cartState

    fun getRestaurantById(restaurantId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getRestaurantById(restaurantId))
        }
    }

    fun addToCart(restaurantId: Long, food: Food, quantity: Int) {
        viewModelScope.launch {
            repository.addToCart(restaurantId, food, quantity)
            _cartState.value = UiState.Success(repository.getCartState())
        }
    }

    fun shouldShowCartItem(food: Food): Boolean {
        return (repository.getCartItem(food)?.quantity ?: 0) > 0
    }
    fun decrementCartItem(food: Food) {
        viewModelScope.launch {
            repository.decrementCartItem(food)
            _cartState.value = UiState.Success(repository.getCartState())
        }
    }
    fun removeFromCart(restaurantId: Long, food: Food) {
        viewModelScope.launch {
            repository.removeFromCart(restaurantId, food)
            _cartState.value = UiState.Success(repository.getCartState())
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
            _cartState.value = UiState.Success(repository.getCartState())
        }
    }
}
