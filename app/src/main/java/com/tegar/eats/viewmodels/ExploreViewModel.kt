package com.tegar.eats.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.data.local.repository.RestaurantRepository
import com.tegar.eats.ui.commons.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ExploreViewModel(private val repository: RestaurantRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Restaurant>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Restaurant>>>
        get() = _uiState


    private val _query = MutableStateFlow("")
    val query =  _query.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllRestaurants().catch { exception ->
                _uiState.value = UiState.Error(exception.message.orEmpty())
            }.collect { restaurants ->
                _uiState.value = UiState.Success(restaurants)
            }

        }
    }

    fun searchRestaurants(newQuery: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _query.value = newQuery
            repository.searchRestaurants(newQuery).collect { filteredRestaurants ->
                _uiState.value = UiState.Success(filteredRestaurants)
            }
        }
    }
}
