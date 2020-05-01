package com.destr.financehelper.presentation.screen.calcualte

import moxy.MvpView

interface CalculateView : MvpView {

    fun setCalculatedNullBestPrice(calculatedNullBestPrice: Double)

    fun setCalculatedSellCostPrice(calculatedSellCostPrice: Double)

    fun setCalculatedConvertedPrice(calculatedConvertedPrice: Double)

    fun hideBestPriceBlock()

    fun hideProfitBlock()

    fun hideConvertedBlock()
}