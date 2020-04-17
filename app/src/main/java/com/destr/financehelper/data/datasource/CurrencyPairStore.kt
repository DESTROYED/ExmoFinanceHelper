package com.destr.financehelper.data.datasource

import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.destr.financehelper.data.datasource.local.Favorite

interface CurrencyPairStore {

    suspend fun getCurrenciesAsync(): List<String>

    suspend fun getPairWithDetails(): Map<String, PairDetail>

    suspend fun getFavoritePairDetails(): List<Favorite>

    suspend fun setFavoriteState(currencyPair: String, isFavorite: Boolean)
}