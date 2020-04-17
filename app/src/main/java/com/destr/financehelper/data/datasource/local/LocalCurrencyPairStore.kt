package com.destr.financehelper.data.datasource.local

import android.content.Context
import com.destr.financehelper.data.datasource.CurrencyPairStore
import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import java.lang.Exception

class LocalCurrencyPairStore(context: Context) : CurrencyPairStore {

    private val dbInstance = FavoriteDatabase.getDatabase(context)

    override suspend fun getCurrenciesAsync() = throw Exception()

    override suspend fun getPairWithDetails(): Map<String, PairDetail> = throw Exception()

    override suspend fun getFavoritePairDetails() =
        dbInstance?.favoriteDao()?.getAllCurrencyFavoritePairs().orEmpty()

    override suspend fun setFavoriteState(currencyPair: String, isFavorite: Boolean) {
        dbInstance?.favoriteDao()?.addCurrencyPair(Favorite(currencyPair, isFavorite))
    }
}