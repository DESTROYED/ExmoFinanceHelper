package com.destr.financehelper.data.datasource.cloud.response

import com.destr.financehelper.data.datasource.local.Favorite
import com.destr.financehelper.domain.CurrencyPair

fun Map<String, PairDetail>.getCurrencyPairList(currencyPairList: List<Favorite>): List<CurrencyPair> =
    map {
        return@map CurrencyPair(
            it.key.split("_")[0],
            it.key.split("_")[1],
            it.key.replace("_", "/"),
            it.value.avg,
            currencyPairList.find { currencyPair ->
                currencyPair.currencyPair == it.key.replace("_", "/")
            } != null
        )
    }