package com.destr.financehelper.data.datasource.cloud

import com.destr.financehelper.data.datasource.CurrencyPairStore
import java.lang.Exception

class CloudCurrencyPairStore : CurrencyPairStore {

    override suspend fun getCurrenciesAsync() = NetworkClient.getCurrenciesAsync()

    override suspend fun getPairWithDetails() = NetworkClient.getPairWithDetails()

    override suspend fun getFavoritePairDetails(): List<String> = throw Exception()

    override suspend fun setFavoriteState(currencyPair: String, isFavorite: Boolean) =
        throw Exception()
}