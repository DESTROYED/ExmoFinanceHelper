package com.destr.financehelper.domain

data class CurrencyPair(
    val firstCurrency: String,
    val secondCurrency: String,
    val pairName: String,
    val avg: String,
    var isFavorite: Boolean = false
)
