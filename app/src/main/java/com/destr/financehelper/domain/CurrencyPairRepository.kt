package com.destr.financehelper.domain

interface CurrencyPairRepository {

    suspend fun getCurrenciesAsync(): List<String>

    suspend fun getPairWithDetails(): List<CurrencyPair>

    suspend fun getFavoritePairDetails(): List<CurrencyPair>

    suspend fun setFavoriteState(currencyPair: String, isFavorite: Boolean): Unit?
}