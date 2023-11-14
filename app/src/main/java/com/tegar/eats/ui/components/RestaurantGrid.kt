package com.tegar.eats.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.tegar.eats.data.local.model.Restaurant

@Composable
fun RestaurantGrid(
    listRestaurant: List<Restaurant>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("restaurantsList")
    )
    {
        items(listRestaurant, key = { it.restaurantName }) { restaurant ->
            RestaurantItem(restaurant = restaurant, navigateToDetail = navigateToDetail)
        }
    }
}