package com.tegar.eats.ui.components.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun SkeletonRestaurantDetail(modifier : Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            item() {
                Box {
                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(223.dp)
                            .shimmer(),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray)
                        )
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))
                SkeletonRowItem()
                Spacer(modifier = Modifier.height(16.dp))


            }
            items(5){
                SkeletonFoodItem()

            }


            }

        }





}