package com.tegar.eats.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.data.local.repository.RestaurantRepository
import com.tegar.eats.ui.commons.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val restaurantRepository: RestaurantRepository) : ViewModel() {
    private val _restaurantsState: MutableStateFlow<UiState<List<Restaurant>>> = MutableStateFlow(UiState.Loading)
    val restaurantsState: StateFlow<UiState<List<Restaurant>>>
        get() = _restaurantsState

    fun fetchAllRestaurants() {
        viewModelScope.launch {
            restaurantRepository.getAllRestaurants().catch { exception ->
                _restaurantsState.value = UiState.Error(exception.message.orEmpty())
            }.collect { restaurants ->
                _restaurantsState.value = UiState.Success(restaurants)
            }
        }
    }
}
