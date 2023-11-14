package com.tegar.eats.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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
import com.tegar.eats.ui.components.skeleton.SkeletonLoadingRestaurantRow
import com.tegar.eats.viewmodels.HomeViewModel
import com.tegar.eats.viewmodels.ViewModelFactory


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    navigateToSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())

    ) {
        Box(Modifier.clickable {
            navigateToDetail
        }) {
            CostumeForm(
                value = query,
                onValueChange = { newValue -> query = newValue },
                onImeActionPerformed = { action ->
                    if (action == ImeAction.Done && query.text.isNotEmpty()) {
                        navigateToSearch(query.text)
                    }
                }
            )
        }
        UserBalanceCard(
            balance = stringResource(id = R.string.balance_dummy),
            onTopUpClick = { },
            onPayClick = {})
        ListSection(
            title = stringResource(R.string.section_category),
            content = { CategoryRow(dummyCategory) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        viewModel.restaurantsState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    ListSection(
                        title = stringResource(R.string.section_top_restaurant),
                        content = {
                            SkeletonLoadingRestaurantRow()
                        }
                    )
                    viewModel.fetchAllRestaurants()
                }
                is UiState.Success -> {

                    if(uiState.data.isEmpty()){
                        Text(stringResource(id = R.string.empty_store_message))
                    }else{
                        ListSection(
                            title = stringResource(R.string.section_top_restaurant),
                            content = {
                                RestaurantRow(
                                    uiState.data,
                                    navigateToDetail = navigateToDetail
                                )
                            }
                        )
                    }
                    

                }

                is UiState.Error -> {
                    Text(stringResource(id = R.string.error_message))
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

