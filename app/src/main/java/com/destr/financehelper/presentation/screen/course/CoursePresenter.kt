package com.destr.financehelper.presentation.screen.course

import com.destr.financehelper.domain.CurrencyPair
import com.destr.financehelper.presentation.ExmoFinanceHelperApplication
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
    private var pairsMap: List<CurrencyPair> = emptyList()
    private var filteredPairs: List<CurrencyPair> = emptyList()
    private var coursePairRepository = ExmoFinanceHelperApplication().currencyPairRepository

    fun onStart(isFavoritePage: Boolean) {
        loadCurrencies()
        loadPairs(isFavoritePage)
    }

    fun refreshCourses(isFavoritePage: Boolean) {
        presenterUIScope.launch { viewState.setPairs(null) }
        onStart(isFavoritePage)
    }

    private fun loadPairs(isFavoritePage: Boolean) = presenterIOScope.launch {
        with(ExmoFinanceHelperApplication().currencyPairRepository) {
            pairsMap = if (isFavoritePage) getFavoritePairDetails() else getPairWithDetails()
            presenterUIScope.launch { viewState.setPairs(pairsMap) }
        }
    }

    private fun filterBySecondCurrency(currency: String) =
        viewState.setPairs(filteredPairs.filter { it.pairName.contains(currency) })

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

    fun onCourseItemLongClick(currencyPair: CurrencyPair) {
        presenterIOScope.launch {
            coursePairRepository.setFavoriteState(currencyPair.pairName, currencyPair.isFavorite)
        }
    }

    private fun loadCurrencies() = presenterIOScope.launch {
        currencies = coursePairRepository.getCurrenciesAsync()
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

    private fun isPairContainsCurrency(pairDetail: CurrencyPair, currency: String) =
        pairDetail.firstCurrency == currency || pairDetail.secondCurrency == currency

    private fun getFirstCurrencyFromPair(pairDetail: CurrencyPair) =
        pairDetail.firstCurrency

    private fun getSecondCurrencyFromPair(pairDetail: CurrencyPair) =
        pairDetail.secondCurrency

    private fun presetCurrencies(localCurrencies: List<String>): List<String> {
        val firstCurrencies = mutableListOf<String>()
        firstCurrencies.add("Select currency")
        firstCurrencies.addAll(localCurrencies)
        return firstCurrencies
    }
}