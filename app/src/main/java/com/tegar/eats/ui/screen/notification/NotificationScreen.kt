package com.tegar.eats.ui.screen.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    modifier : Modifier = Modifier,
            searchQuery: String
){
    Box {
        Text("Search Results for: $searchQuery")
    }
}