package com.tegar.eats.di

import com.tegar.eats.data.local.repository.RestaurantRepository

object Injection {
    fun provideRepository(): RestaurantRepository {
        return RestaurantRepository.getInstance()
    }
}