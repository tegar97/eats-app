package com.tegar.eats.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.tegar.eats.data.local.model.Restaurant


@Composable
fun RestaurantRow(
    listRestaurant: List<Restaurant>,
    testTag : String = "restaurantsList",
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,

    ) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.testTag(testTag)
    ) {
        items(listRestaurant, key = { it.restaurantName }) { restaurant ->
            RestaurantItem(restaurant = restaurant, navigateToDetail = navigateToDetail)
        }
    }
}



//@Composable
//@Preview(showBackground = true)
//fun RestaurantRowPreview(){
//    EatsTheme {
//        RestaurantRow(dummyRestaurantList)
//    }
//}