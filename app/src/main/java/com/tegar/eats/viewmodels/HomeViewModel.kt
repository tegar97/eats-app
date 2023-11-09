package com.tegar.eats.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.data.local.repository.RestaurantRepository
import com.tegar.eats.ui.commons.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository : RestaurantRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Restaurant>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Restaurant>>>
        get() = _uiState

    fun getAllRestaurants() {
        viewModelScope.launch {
            repository.getAllRestaurants().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect{ restaurant ->
                _uiState.value = UiState.Success(restaurant)
            }
        }
    }

}