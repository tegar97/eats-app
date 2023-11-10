package com.tegar.eats.utils

import java.text.NumberFormat
import java.util.*

object CurrencyFormatter {
    val rupiahFormat: NumberFormat by lazy {
        NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    }
}
