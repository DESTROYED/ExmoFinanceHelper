package com.destr.financehelper.data.datasource.cloud

import com.destr.financehelper.data.datasource.CurrencyPairStore
import com.destr.financehelper.data.datasource.local.Favorite
import java.lang.Exception

class CloudCurrencyPairStore : CurrencyPairStore {

    override suspend fun getCurrenciesAsync() = NetworkClient.getCurrenciesAsync()

    override suspend fun getPairWithDetails() = NetworkClient.getPairWithDetails()

    override suspend fun getFavoritePairDetails(): List<Favorite> = throw Exception()

    override suspend fun setFavoriteState(currencyPair: String, isFavorite: Boolean) =
        throw Exception()
}