package com.destr.financehelper.presentation.screen.course

import com.destr.financehelper.data.datasource.CurrencyPairRepositoryImpl
import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CoursePresenter : MvpPresenter<CourseView>() {

    private val presenterJob = SupervisorJob()
    private val presenterUIScope = CoroutineScope(Dispatchers.Main + presenterJob)
    private val presenterIOScope = CoroutineScope(Dispatchers.IO + presenterJob)

    private var currencies: List<String> = emptyList()
    private var secondCurrencies: List<String> = emptyList()
    private var pairsMap: Map<String, PairDetail> = emptyMap()
    private var filteredPairs: Map<String, PairDetail> = emptyMap()

    fun onStart() {
        loadCurrencies()
        loadPairs()
    }

    private fun loadPairs() = presenterIOScope.launch {
        pairsMap = CurrencyPairRepositoryImpl.getPairWithDetails().orEmpty()
        presenterUIScope.launch { viewState.setPairs(pairsMap) }
    }

    private fun filterBySecondCurrency(currency: String) =
        viewState.setPairs(filteredPairs.filter { it.key.contains(currency) })

    private fun getSecondCurrencyFromPairs(currency: String): List<String> =
        filteredPairs.filter { isPairContainsCurrency(it, currency) }.map { pairDetail ->
            when (currency) {
                getFirstCurrencyFromPair(pairDetail) -> getSecondCurrencyFromPair(pairDetail)
                getSecondCurrencyFromPair(pairDetail) -> getFirstCurrencyFromPair(pairDetail)
                else -> ""
            }
        }

    fun onFirstCurrencyViewClicked(position: Int) =
        if (position != 0) loadSecondCurrency(currencies[position - 1])
        else loadSecondCurrency("")

    fun onSecondCurrencyViewClicked(position: Int) =
        if (position != 0) filterBySecondCurrency(secondCurrencies[position - 1])
        else filterBySecondCurrency("")

    fun onCourseItemLongClick(position: Int, currencyValue: String) {
        presenterIOScope.launch {
            CurrencyPairRepositoryImpl.currencyPairFactory.createLocalPairStorage()
                .setFavoriteState(currencyValue, true)
        }
    }

    private fun loadCurrencies() = presenterIOScope.launch {
        currencies = CurrencyPairRepositoryImpl.getCurrenciesAsync().orEmpty()
        presenterUIScope.launch { viewState.setFirstCurrency(presetCurrencies(currencies)) }
    }

    private fun loadSecondCurrency(currency: String) {
        filteredPairs = if (currency.isNotEmpty()) {
            pairsMap.filter { pairDetail -> isPairContainsCurrency(pairDetail, currency) }
        } else {
            pairsMap
        }
        secondCurrencies = getSecondCurrencyFromPairs(currency)
        viewState.setPairs(filteredPairs)
        viewState.setSecondCurrency(presetCurrencies(secondCurrencies))
    }

    private fun isPairContainsCurrency(
        pairDetail: Map.Entry<String, PairDetail>,
        currency: String
    ) =
        pairDetail.key.split("_")[0] == currency
                || pairDetail.key.split("_")[1] == currency

    private fun getFirstCurrencyFromPair(pairDetail: Map.Entry<String, PairDetail>) =
        pairDetail.key.split("_")[0]

    private fun getSecondCurrencyFromPair(pairDetail: Map.Entry<String, PairDetail>) =
        pairDetail.key.split("_")[1]

    private fun presetCurrencies(localCurrencies: List<String>): List<String> {
        val firstCurrencies = mutableListOf<String>()
        firstCurrencies.add("Select currency")
        firstCurrencies.addAll(localCurrencies)
        return firstCurrencies
    }
}