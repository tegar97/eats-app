package com.tegar.eats.data.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category (
    @DrawableRes val categoryIcon : Int,
    @StringRes val textCategory : Int

)