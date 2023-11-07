package com.tegar.eats.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Restaurant(
    @DrawableRes val restaurantImage : Int,
    val restaurantName : String,
    val distance : String,
    val estimateDistance : String,
    val rating : Double,
    val ratingCount : Int,

    )