package com.destr.financehelper.data.datasource

import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.destr.financehelper.data.datasource.cloud.response.getCurrencyPairList
import com.destr.financehelper.domain.CurrencyPair
import com.destr.financehelper.domain.CurrencyPairRepository

object CurrencyPairRepositoryImpl : CurrencyPairRepository {

    lateinit var currencyPairFactory: CurrencyPairFactory

    private var currencyPairWithDetail: Map<String, PairDetail>? = null
    private var updateTimeStamp: Long = 0

    override suspend fun getCurrenciesAsync() =
        currencyPairFactory.createCloudPairStorage().getCurrenciesAsync()

    override suspend fun getPairWithDetails(): List<CurrencyPair> {

        if (currencyPairWithDetail == null || System.currentTimeMillis() - updateTimeStamp > 3000) {
            currencyPairWithDetail =
                currencyPairFactory.createCloudPairStorage().getPairWithDetails()

            updateTimeStamp = System.currentTimeMillis()
        }
        val favorites = currencyPairFactory.createLocalPairStorage().getFavoritePairDetails()
        return currencyPairWithDetail.orEmpty().getCurrencyPairList(favorites)
    }

    override suspend fun getOnlyFavoritesPairs(): List<CurrencyPair> =
        getPairWithDetails().filter { it.isFavorite }

    override suspend fun getFavoritePairDetails() = getPairWithDetails().filter {
        currencyPairFactory.createLocalPairStorage().getFavoritePairDetails().find { favorite -> favorite.currencyPair == it.pairName } != null
    }

    override suspend fun setFavoriteState(currencyPair: String, isFavorite: Boolean) =
        currencyPairFactory.createLocalPairStorage().setFavoriteState(currencyPair, isFavorite)
}