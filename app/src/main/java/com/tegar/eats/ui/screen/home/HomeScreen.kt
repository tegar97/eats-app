package com.tegar.eats.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.R
import com.tegar.eats.data.dummyCategory
import com.tegar.eats.data.dummyRestaurantList
import com.tegar.eats.data.popularList
import com.tegar.eats.model.Category
import com.tegar.eats.ui.components.CategoryRow
import com.tegar.eats.ui.components.CircleAction
import com.tegar.eats.ui.components.CostumeForm
import com.tegar.eats.ui.components.ListSection
import com.tegar.eats.ui.components.RestaurantRow
import com.tegar.eats.ui.components.UserCard
import com.tegar.eats.ui.theme.DarkBlue
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.ui.theme.ManropeFamiliy


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var costumeFormValue by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Menggunakan CostumeForm dengan state dan onValueChange
            CostumeForm(
                value = costumeFormValue,
                onValueChange = { newValue -> costumeFormValue = newValue }
            )
            Spacer(modifier = Modifier.weight(1.0f))
            CircleAction(icon = R.drawable.ic_filter, onClick = { /*TODO*/ })
        }

        UserCard(balance = "Rp 1.253.00", onTopUpClick = { /*TODO*/ } , onPayClick = {})

        ListSection(
            title = stringResource(R.string.section_category),
            content = { CategoryRow(dummyCategory) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListSection(
            title = stringResource(R.string.section_top_restaurant),
            content = { RestaurantRow(dummyRestaurantList) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        ListSection(
            title = stringResource(R.string.section_favorite_restaurant),
            content = { RestaurantRow(popularList) }
        )
        Spacer(modifier = Modifier.height(16.dp))



    }
}



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    EatsTheme {
        HomeScreen()
    }


}