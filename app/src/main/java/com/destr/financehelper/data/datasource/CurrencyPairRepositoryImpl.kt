package com.destr.financehelper.data.datasource

import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.destr.financehelper.domain.CurrencyPairRepository

object CurrencyPairRepositoryImpl : CurrencyPairRepository {

    lateinit var currencyPairFactory: CurrencyPairFactory

    private var currencyPairWithDetail: Map<String, PairDetail>? = null
    private var updateTimeStamp: Long = 0

    override suspend fun getCurrenciesAsync() =
        currencyPairFactory.createCloudPairStorage().getCurrenciesAsync()

    override suspend fun getPairWithDetails(): Map<String, PairDetail>? {
        if (currencyPairWithDetail == null || System.currentTimeMillis() - updateTimeStamp > 3000) {
            currencyPairWithDetail =
                currencyPairFactory.createCloudPairStorage().getPairWithDetails()
            updateTimeStamp = System.currentTimeMillis()

        }
        return currencyPairWithDetail
    }

    override suspend fun getFavoritePairDetails() =
        getPairWithDetails().orEmpty().filterKeys {
            currencyPairFactory.createLocalPairStorage().getFavoritePairDetails().contains(it)
        }

    override suspend fun addFavoritePair(currencyPair: String, isFavorite: Boolean) =
        currencyPairFactory.createLocalPairStorage().setFavoriteState(currencyPair, isFavorite)
}