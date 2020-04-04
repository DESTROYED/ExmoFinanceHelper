package com.destr.financehelper.presentation.screen.course

import com.destr.financehelper.data.datasource.cloud.Dao
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
    private var pairsMap: Map<String, PairDetail> = emptyMap()
    private var filteredPairs: Map<String, PairDetail> = emptyMap()

    fun onStart() {
        loadCurrencies()
        loadPairs()
    }

    private fun loadCurrencies() = presenterIOScope.launch {
        currencies = Dao.SERVER_DAO.getCurrenciesAsync()
        presenterUIScope.launch {
            val firstCurrencies = mutableListOf<String>()
            firstCurrencies.add("Select currency")
            firstCurrencies.addAll(currencies)
            viewState.setFirstCurrency(currencies)
        }
    }

    private fun loadPairs() = presenterIOScope.launch {
        pairsMap = Dao.SERVER_DAO.getPairWithDetails()
        presenterUIScope.launch { viewState.setPairs(pairsMap) }
    }

    fun filterBySecondCurrency(currency: String) =
        viewState.setPairs(filteredPairs.filter { it.key.contains(currency) })

    private fun getSecondCurrencyFromPairs(
        filteredPairs: Map<String, PairDetail>,
        currency: String
    ): List<String> {
        val secondCurrencyList = mutableListOf<String>()
        filteredPairs.forEach {
            if (it.key.contains(currency + "_")) {
                secondCurrencyList.add(it.key.substring(currency.length + 1))
            } else {
                secondCurrencyList.add(it.key.substring(0, it.key.length - currency.length - 1))
            }
        }
        return secondCurrencyList
    }

    fun onFirstCurrencyViewClicked(position: Int) {
        if (position != 0) loadSecondCurrency(currencies[position - 1])
        else loadSecondCurrency("")
    }

    private fun loadSecondCurrency(currency: String) {
        filteredPairs = pairsMap.filter { pairDetail -> pairDetail.key.contains(currency) }
        viewState.setPairs(filteredPairs)
        viewState.setSecondCurrency(getSecondCurrencyFromPairs(filteredPairs, currency))
    }
}