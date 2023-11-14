package com.tegar.eats.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.R
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.utils.LocalCustomColorsPalette

@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,

    ) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, LocalCustomColorsPalette.current.costumeBorderColor),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .width(153.dp)
            .clickable {
                navigateToDetail(restaurant.id)
            },

        ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Image(
                painter = painterResource(id = restaurant.restaurantImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEndPercent = 12,
                            bottomStartPercent = 12
                        )
                    ),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        restaurant.distance,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xff8C8C8C)
                        )
                    )

                    Box(
                        Modifier
                            .size(3.dp)
                            .background(
                                color = Color(0xffD9D9D9),
                                shape = CircleShape
                            )
                    )
                    Text(
                        restaurant.estimateDistance,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xff8C8C8C)
                        )
                    )
                }
                Text(
                    restaurant.restaurantName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.displaySmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = stringResource(id = R.string.rating_star_icon),
                        modifier = Modifier.size(11.dp)
                    )

                    Text(
                        restaurant.rating.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xff8C8C8C),
                            fontSize = 12.sp
                        )
                    )
                    Box(
                        Modifier
                            .size(3.dp)
                            .background(
                                color = Color(0xffD9D9D9),
                                shape = CircleShape
                            )
                    )
                    Text(
                        stringResource(R.string.rating_count_text, restaurant.ratingCount),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xff8C8C8C),
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}