package com.tegar.eats.data

import com.tegar.eats.R
import com.tegar.eats.model.Category

val dummyCategory = listOf(
    R.drawable.ic_maps to R.string.near_category,
    R.drawable.ic_promos to R.string.promo_category,
    R.drawable.ic_healthy to R.string.healty_category,
    R.drawable.ic_best_seller to R.string.best_seller_category,
    ).map { Category(it.first, it.second) }