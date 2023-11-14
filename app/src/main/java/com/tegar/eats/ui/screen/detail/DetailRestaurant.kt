package com.tegar.eats.ui.screen.detail


import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tegar.eats.R
import com.tegar.eats.data.local.model.CartItem
import com.tegar.eats.data.local.model.Food
import com.tegar.eats.data.local.model.Restaurant
import com.tegar.eats.di.Injection
import com.tegar.eats.ui.commons.UiState
import com.tegar.eats.ui.components.FoodItem
import com.tegar.eats.ui.components.RestaurantInformationCard
import com.tegar.eats.ui.components.skeleton.SkeletonRestaurantDetail
import com.tegar.eats.utils.CurrencyFormatter
import com.tegar.eats.utils.LocalCustomColorsPalette
import com.tegar.eats.viewmodels.DetailRestaurantViewModel
import com.tegar.eats.viewmodels.ViewModelFactory

@Composable
fun DetailRestaurant(
    restaurantId: Long,
    navigateBack: () -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    viewModel: DetailRestaurantViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),

) {


    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val cartState by viewModel.cartState.collectAsState()

    when (uiState) {
        is UiState.Loading -> {
            SkeletonRestaurantDetail()
            viewModel.getRestaurantById(restaurantId)
            viewModel.getCart()
        }

        is UiState.Success -> {
            val data = (uiState as UiState.Success<Restaurant>).data
            val cartStateData = (cartState as UiState.Success<CartState>).data

            val totalFoodPrice: Int = cartStateData.totalPrice
            var formattedTotalFoodPrice: String by remember { mutableStateOf("") }
            val formattedTotal = CurrencyFormatter.rupiahFormat.format(totalFoodPrice)
            formattedTotalFoodPrice = formattedTotal


            DetailContent(
                data.restaurantName,
                data.address,
                data.distance,
                data.restaurantImage,
                data.rating,
                data.ratingCount,
                data.foods,
                cartStateData.item,
                formattedTotalFoodPrice,

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
                onBackClick = navigateBack,
                onOrderButtonClicked

            )
        }

        is UiState.Error -> {
            Text(stringResource(id = R.string.error_message))
        }
    }
}


@Composable
fun DetailContent(
    restaurantName: String,
    address: String,
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
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val shareReceipt = stringResource(
        R.string.share_message,
        restaurantName,
        totalFoodPrice
    )

    Box(
        modifier = modifier.fillMaxSize(),

    ) {
        LazyColumn(   contentPadding = PaddingValues( bottom = 60.dp),) {
            item {
                Box{
                    Image(
                        painter = painterResource(id = restaurantImg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .fillMaxWidth()

                            .height(223.dp)
                    )
                  Row(
                      horizontalArrangement  = Arrangement.SpaceBetween,
                      modifier = Modifier.padding(16.dp).fillMaxWidth()){

                      AppBarIcon(
                          icon = Icons.Default.ArrowBack,
                          iconContentDescription =  R.string.back,
                          onClick = onBackClick
                      )
                      Row(
                          horizontalArrangement  = Arrangement.spacedBy(12.dp),

                          ){
                          AppBarIcon(
                              icon = Icons.Default.FavoriteBorder,
                              iconContentDescription =  R.string.favorite,
                              onClick = {}
                          )
                          AppBarIcon(
                              icon = Icons.Default.Search,
                              iconContentDescription =  R.string.search_food,
                              onClick = {}
                          )
                      }
                  }


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
                            address = address,
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
                    foodPrice = CurrencyFormatter.rupiahFormat.format(food.foodPrice),
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
                    Text(
                        stringResource(id = R.string.total_price_text),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        totalFoodPrice,
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.testTag("totalPrice")
                    )

                }
                FilledTonalButton(
                    onClick = {
                        onOrderButtonClicked(shareReceipt)
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
                        stringResource(id = R.string.checkout_text),
                        style = MaterialTheme.typography.labelSmall.copy(
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
fun AppBarIcon(
     icon : ImageVector,
    @StringRes iconContentDescription : Int,

    onClick: () -> Unit,
) {
    Surface(
        modifier= Modifier.background(
            color = LocalCustomColorsPalette.current.costumeBlackWhite,
            shape = CircleShape
        ).size(44.dp) .clickable { onClick() }
    ){

        Icon(
            imageVector = icon,
            tint = LocalCustomColorsPalette.current.costumeWhiteBlack,
            contentDescription = stringResource(iconContentDescription),
            modifier = Modifier
                .padding(10.dp)

        )
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
                contentDescription = stringResource(id = R.string.voucher_content_description),
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





