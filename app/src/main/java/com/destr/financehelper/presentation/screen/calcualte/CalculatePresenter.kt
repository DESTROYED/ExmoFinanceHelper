package com.destr.financehelper.presentation.screen.calcualte

import com.destr.financehelper.domain.CalculateEntity
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CalculatePresenter : MvpPresenter<CalculateView>() {

    fun onCostTextChanged(
        entrancePrice: Double,
        cost: Double,
        sellCost: Double,
        convertingPrice: Double
    ) {
        calculateNullBestPrice(cost)
        calculateSellPrice(entrancePrice, cost, sellCost)
        calculatedConvertedPrice(entrancePrice, cost, sellCost, convertingPrice)
    }

    fun onSellOrEntrancePriceTextChanged(
        entrancePrice: Double,
        cost: Double,
        sellCost: Double,
        convertingPrice: Double
    ) {
        calculateSellPrice(entrancePrice, cost, sellCost)
        calculatedConvertedPrice(entrancePrice, cost, sellCost, convertingPrice)
    }

    fun onConvertingCourseTextChanged(
        entrancePrice: Double,
        cost: Double,
        sellCost: Double,
        convertingPrice: Double
    ) = calculatedConvertedPrice(entrancePrice, cost, sellCost, convertingPrice)

    private fun calculateSellPrice(entrancePrice: Double, cost: Double, sellCost: Double) =
        if (isSellCostPriceValid(entrancePrice, cost, sellCost)) {
            viewState.setCalculatedSellCostPrice(
                CalculateEntity().getCostDiff(entrancePrice, cost, sellCost)
            )
        } else {
            viewState.hideProfitBlock()
        }

    private fun calculateNullBestPrice(cost: Double) =
        if (cost != .0) viewState.setCalculatedNullBestPrice(CalculateEntity().getNotLossCost(cost))
        else viewState.hideBestPriceBlock()

    private fun calculatedConvertedPrice(
        entrancePrice: Double,
        cost: Double,
        sellCost: Double,
        convertingPrice: Double
    ) =
        if (isConvertedPriceValid(entrancePrice, cost, sellCost, convertingPrice)) {
            viewState.setCalculatedConvertedPrice(
                CalculateEntity().getCostDiff(entrancePrice, cost, sellCost) * convertingPrice
            )
        } else {
            viewState.hideConvertedBlock()
        }

    private fun isSellCostPriceValid(entrancePrice: Double, cost: Double, sellCost: Double) =
        entrancePrice != .0 && cost != .0 && sellCost != .0

    private fun isConvertedPriceValid(
        entrancePrice: Double,
        cost: Double,
        sellCost: Double,
        convertingCourse: Double
    ) = entrancePrice != .0 && cost != .0 && sellCost != .0 && convertingCourse != .0
}