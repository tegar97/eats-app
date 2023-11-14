package com.tegar.eats.data.local.model

import androidx.annotation.DrawableRes

data class Restaurant(
    val id : Long,
    @DrawableRes val restaurantImage : Int,
    val restaurantName : String,
    val distance : String,
    val estimateDistance : String,
    val rating : Double,
    val ratingCount : Int,
    val foods : List<Food>,
    val address : String

    )