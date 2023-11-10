package com.tegar.eats.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.utils.LocalCustomColorsPalette


@Composable
fun FoodItem(
    @DrawableRes foodImg: Int,
    foodName: String,
    foodDesc: String,
    foodPrice: Int,
    foods: List<Food>,
    food: Food,
    cartItems: List<CartItem>,
    isShowingQuantity: Boolean,
    onAddToCart: (Int, Int) -> Unit,
    onRemoveFromCart: (CartItem) -> Unit,
    onDecrementCartItem: (Food) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(
            horizontal = 16.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Image(
                painter = painterResource(id = foodImg), contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,


                )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = foodName,
                    style = MaterialTheme.typography.displaySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = foodDesc,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LocalCustomColorsPalette.current.costumeFontSubtitleColor
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    "Rp $foodPrice", style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

        }
        if (isShowingQuantity) {
            ButtonQuantity(
                food = food,
                cartItems = cartItems,
                onAddToCart = onAddToCart,
                onRemoveFromCart = onRemoveFromCart,
                onDecrementCartItem = onDecrementCartItem,
                foods = foods
            )
        } else {
            FilledTonalButton(
                onClick = {
                    val foodIndex = foods.indexOf(food)
                    onAddToCart(foodIndex, 1)
                },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(
                    vertical = 0.dp,
                    horizontal = 20.dp
                ),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFFDED4), contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Add", style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Default,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ))
            }
        }
    }
}
