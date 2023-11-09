package com.tegar.eats.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tegar.eats.R
import com.tegar.eats.data.local.fake.dummyCategory
import com.tegar.eats.di.Injection
import com.tegar.eats.ui.commons.UiState
import com.tegar.eats.ui.components.CategoryRow
import com.tegar.eats.ui.components.CostumeForm
import com.tegar.eats.ui.components.ListSection
import com.tegar.eats.ui.components.RestaurantRow
import com.tegar.eats.ui.components.UserBalanceCard
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.viewmodels.HomeViewModel
import com.tegar.eats.viewmodels.ViewModelFactory


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail : (Long) -> Unit

) {
    var costumeFormValue by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())

    ) {
        CostumeForm(
            value = costumeFormValue,
            onValueChange = { newValue -> costumeFormValue = newValue }
        )


        UserBalanceCard(balance = "Rp 1.253.00", onTopUpClick = { /*TODO*/ }, onPayClick = {})

        ListSection(
            title = stringResource(R.string.section_category),
            content = { CategoryRow(dummyCategory) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllRestaurants()
                }

                is UiState.Success -> {
                    ListSection(
                        title = stringResource(R.string.section_top_restaurant),
                        content = { RestaurantRow(uiState.data, navigateToDetail =  navigateToDetail) }
                    )
                }

                is UiState.Error -> {}
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
//
//        ListSection(
//            title = stringResource(R.string.section_favorite_restaurant),
//            content = { RestaurantRow(popularList) }
//        )
        Spacer(modifier = Modifier.height(16.dp))


    }
}


//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun HomeScreenPreview() {
//    EatsTheme {
//        HomeScreen()
//    }
//
//
//}