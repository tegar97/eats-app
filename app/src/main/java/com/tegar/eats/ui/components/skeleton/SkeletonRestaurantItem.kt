package com.tegar.eats.ui.components.skeleton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.R
import com.tegar.eats.utils.LocalCustomColorsPalette
import com.valentinilk.shimmer.shimmer

@Composable
fun SkeletonLoadingRestaurantItem(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, LocalCustomColorsPalette.current.costumeBorderColor),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .width(153.dp)


    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEndPercent = 12,
                            bottomStartPercent = 12
                        )
                    )
                    .shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffE3E3E3))
                )
            }




            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 2.dp,
                        horizontal = 16.dp
                    )
                    .height(10.dp)
                    .shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffE3E3E3))
                )
            }
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .padding(
                        vertical = 5.dp,
                        horizontal = 16.dp
                    )
                    .height(10.dp)
                    .shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffE3E3E3))
                )


            }
        }
    }
}
