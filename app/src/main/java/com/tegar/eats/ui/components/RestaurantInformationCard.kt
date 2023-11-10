package com.tegar.eats.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.R
import com.tegar.eats.ui.theme.DarkYellow
import com.tegar.eats.utils.LocalCustomColorsPalette

@Composable
fun RestaurantInformationCard(
    restaurantName: String,
    address: String,
    rating: Double,
    distance: String,
    greatRate: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                restaurantName, style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 20.sp,
                    color = LocalCustomColorsPalette.current.costumeWhiteBlack
                )
            )
            Text(address, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoSection(
                    icon = R.drawable.ic_star,
                    content = rating,
                    label = stringResource(id = R.string.see_review_label)
                )
                InfoSection(
                    icon = R.drawable.ic_marker,
                    content = distance,
                    label = stringResource(id = R.string.distance_label)
                )
                InfoSection(
                    icon = R.drawable.ic_like,
                    content = greatRate,
                    label = stringResource(id = R.string.great_rate_label)
                )
            }
        }
    }
}

@Composable
fun <T> InfoSection(
    @DrawableRes icon: Int,
    content: T,
    label: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
        ) {
            Image(
                painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(content.toString(), style = MaterialTheme.typography.labelSmall.copy(
                color = LocalCustomColorsPalette.current.costumeWhiteBlack
            ))
        }
        Text(
            label, style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                color = if(label == stringResource(id = R.string.see_review_label)) DarkYellow else LocalCustomColorsPalette.current.costumeFontSubtitleColor
            )
        )
    }
}
