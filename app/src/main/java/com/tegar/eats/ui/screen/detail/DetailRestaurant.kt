package com.tegar.eats.ui.screen.detail

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tegar.eats.R
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.di.Injection
import com.tegar.eats.ui.commons.UiState
import com.tegar.eats.ui.components.RestaurantInformationCard
import com.tegar.eats.ui.components.RestaurantItem
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.utils.LocalCustomColorsPalette
import com.tegar.eats.viewmodels.DetailRestaurantViewModel
import com.tegar.eats.viewmodels.ViewModelFactory
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.tegar.eats.ui.components.FoodItem
import com.tegar.eats.utils.CurrencyFormatter

@Composable
fun DetailRestaurant(
    restaurantId: Long,
    navigateBack: () -> Unit,
    viewModel: DetailRestaurantViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {



    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val cartState by viewModel.cartState.collectAsState(
        initial = UiState.Success(
            CartState(
                emptyList(),
                0
            )
        )
    )


    when (uiState) {
        is UiState.Loading -> {
            viewModel.getRestaurantById(restaurantId)
            viewModel.getCart()
        }

        is UiState.Success -> {
            val data = (uiState as UiState.Success<Restaurant>).data
            val cartState = (cartState as UiState.Success<CartState>).data

            // Gunakan Double atau Int sesuai dengan tipe data totalPrice
            val totalFoodPrice: Int = cartState.totalPrice

            var formattedTotalFoodPrice: String by remember { mutableStateOf("") }

            DisposableEffect(totalFoodPrice) {
                val formattedTotal = CurrencyFormatter.rupiahFormat.format(totalFoodPrice)
                formattedTotalFoodPrice = formattedTotal
                onDispose { }
            }



            DetailContent(
                data.restaurantName,
                data.distance,
                data.restaurantImage,
                data.rating,
                data.ratingCount,
                data.foods,
                cartState.orderReward,
                formattedTotalFoodPrice ,
                isEmpty = { food ->
                    viewModel.shouldShowCartItem(food)
                },
                onAddToCart = { foodIndex, quantity ->
                    viewModel.addToCart(data.id, data.foods[foodIndex], quantity)
                },
                onRemoveFromCart = { cartItem ->
                    viewModel.removeFromCart(cartItem.restaurantId, cartItem.food)
                },
                onDecrementCartItem = { food ->
                    viewModel.decrementCartItem(food)
                },
                onBackClick = navigateBack

            )
        }

        is UiState.Error -> {
            // Handle error state
        }
    }
}


@Composable
fun DetailContent(
    restaurantName: String,
    distance: String,
    restaurantImg: Int,
    rating: Double,
    ratingCount: Int,
    foods: List<Food>,
    cartItems: List<CartItem>,
    totalFoodPrice: String,
    isEmpty: (Food) -> Boolean,
    onAddToCart: (Int, Int) -> Unit,
    onRemoveFromCart: (CartItem) -> Unit,
    onDecrementCartItem: (Food) -> Unit,
    onBackClick: () -> Unit,

    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn(
        ) {

            item() {
                Box {
                    Image(
                        painter = painterResource(id = restaurantImg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(223.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier.padding(16.dp).clickable { onBackClick() }
                    )
                    Surface(
                        shadowElevation = 2.dp,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart)
                            .graphicsLayer(
                                translationY = with(LocalDensity.current) { (42).dp.toPx() },
                                spotShadowColor = Color.Black
                            )

                            .fillMaxWidth()
                    ) {
                        RestaurantInformationCard(
                            restaurantName = restaurantName,
                            address = "South Quarter, South Jakarta",
                            rating = rating,
                            distance = distance,
                            greatRate = ratingCount,
                            modifier = Modifier
                                .background(
                                    LocalCustomColorsPalette.current.costumeCardColor,
                                    RoundedCornerShape(8.dp)
                                )


                        )
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))
                VoucherSection()
                Spacer(modifier = Modifier.height(16.dp))

            }


            items(foods, key = { it.idFood }) { food ->
                FoodItem(
                    foodImg = food.foodImage,
                    foodName = food.foodName,
                    foodDesc = food.shortDescription,
                    foodPrice = food.foodPrice,
                    food = food,
                    cartItems = cartItems,
                    isShowingQuantity = isEmpty(food),
                    onAddToCart = onAddToCart,
                    onRemoveFromCart = onRemoveFromCart,
                    onDecrementCartItem = onDecrementCartItem,
                    foods = foods,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(36.dp))

            }

        }

            AnimatedVisibility(
                visible = cartItems.isNotEmpty(),
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background
                    )
                    .padding(
                        vertical = 16.dp,
                        horizontal = 24.dp
                    )
                    .fillMaxWidth()

                    .align(Alignment.BottomStart)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("Total Price ", style = MaterialTheme.typography.headlineSmall)
                        Text(
                            totalFoodPrice,
                            style = MaterialTheme.typography.displayMedium.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )

                    }
                    FilledTonalButton(
                        onClick = {

                        },
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(
                            vertical = 14.dp,
                            horizontal = 49.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LocalCustomColorsPalette.current.costumeSpecialColor,
                            contentColor = LocalCustomColorsPalette.current.costumeBlackWhite
                        )
                    ) {
                        Text(
                            "Checkout", style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Default,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }



        }


    }


}


@Composable

fun VoucherSection(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_promo),
                contentDescription = "Have a voucher ? ",
                modifier = Modifier.size(40.dp)
            )
            Column {
                Text(
                    stringResource(id = R.string.check_out),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    stringResource(id = R.string.promos_available),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp,
                        color = LocalCustomColorsPalette.current.costumeFontSubtitleColor
                    )
                )
            }
        }

        Surface(
            modifier = Modifier
                .background(
                    color = LocalCustomColorsPalette.current.costumeSpecialColor,
                    shape = RoundedCornerShape(100)
                )
                .padding(4.dp)
        ) {
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = null,
                tint = LocalCustomColorsPalette.current.costumeBlackWhite
            )
        }
    }
}





