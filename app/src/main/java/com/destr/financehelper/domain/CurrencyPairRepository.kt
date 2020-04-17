package com.destr.financehelper.domain

import com.destr.financehelper.data.datasource.cloud.response.PairDetail

public interface CurrencyPairRepository {

    suspend fun getCurrenciesAsync(): List<String>?

    suspend fun getPairWithDetails(): Map<String, PairDetail>?

    suspend fun getFavoritePairDetails(): Map<String, PairDetail>?

    suspend fun addFavoritePair(currencyPair: String, isFavorite: Boolean): Unit?
}