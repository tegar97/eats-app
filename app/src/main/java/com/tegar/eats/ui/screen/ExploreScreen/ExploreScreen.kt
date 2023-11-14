package com.tegar.eats.ui.screen.ExploreScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tegar.eats.R
import com.tegar.eats.di.Injection
import com.tegar.eats.ui.commons.UiState
import com.tegar.eats.ui.components.RestaurantGrid
import com.tegar.eats.ui.components.skeleton.SkeletonLoadingRestaurantRow
import com.tegar.eats.ui.theme.Orange
import com.tegar.eats.viewmodels.ExploreViewModel
import com.tegar.eats.viewmodels.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    searchQuery: String,
    navigateToDetail: (Long) -> Unit,
    viewModel: ExploreViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    modifier: Modifier = Modifier,

    ) {

    val query by viewModel.query.collectAsState(initial = if (searchQuery != "{searchQuery}") searchQuery else "")

    Column(modifier) {

        OutlinedTextField(
            label = { Text(stringResource(id = R.string.search_label)) },
            value = query,
            onValueChange = viewModel::searchRestaurants,
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Orange,
                unfocusedBorderColor = Color(0xffE9EBEE)
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("search"),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    KeyboardActions { ImeAction.Done }
                }
            )
        )

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    SkeletonLoadingRestaurantRow()
                    if (searchQuery.isNotEmpty()) {
                        viewModel.searchRestaurants(searchQuery)
                    }
                    viewModel.searchRestaurants(query)
                }

                is UiState.Success -> {
                    RestaurantGrid(
                        uiState.data,
                        navigateToDetail = navigateToDetail
                    )
                }

                is UiState.Error -> {
                    Text(stringResource(id = R.string.error_message))
                }
            }

        }
    }
}