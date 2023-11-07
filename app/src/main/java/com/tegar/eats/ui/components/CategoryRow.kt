package com.tegar.eats.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tegar.eats.model.Category
import com.tegar.eats.utils.LocalCustomColorsPalette

@Composable
fun CategoryRow(
    listMenu: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.textCategory }) { category ->
            CategoryItem(category.categoryIcon,category.textCategory)
        }
    }
}


@Composable
fun CategoryItem(@DrawableRes categoryIcon : Int, @StringRes textCategory : Int, modifier : Modifier = Modifier ){
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment =  Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = LocalCustomColorsPalette.current.costumeSpecialBackground,
                    shape = RoundedCornerShape(20)
                )
        ){
            Image(
                painter = painterResource(id = categoryIcon),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)

            )
        }


        Text(
            text = stringResource(textCategory),
            style = MaterialTheme.typography.bodyMedium,

            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)

        )
    }
}