package com.tegar.eats.data.local.model

import androidx.annotation.DrawableRes

data class  Food (
    val idFood : Long,
    @DrawableRes val foodImage : Int,
    val foodName : String,
    val foodPrice : Int,
    val shortDescription : String,
)