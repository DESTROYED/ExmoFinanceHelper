package com.destr.financehelper.presentation.screen.calcualte

import com.destr.financehelper.domain.CalculateEntity
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CalculatePresenter : MvpPresenter<CalculateView>() {

    fun calculateSellCostPrice(entrancePrice: Double, cost: Double, sellCost: Double) =
        viewState.setCalculatedSellCostPrice(
            CalculateEntity().getCostDiff(
                entrancePrice,
                cost,
                sellCost
            )
        )

    fun calculateNullBestPrice(cost: Double) =
        viewState.setCalculatedNullBestPrice(
            CalculateEntity().getNotLossCost(
                cost
            )
        )

    fun calculatedConvertedPrice(
        entrancePrice: Double, cost: Double, sellCost: Double, convertingPrice: Double
    ) = viewState.setCalculatedConvertedPrice(
        CalculateEntity().getCostDiff(
            entrancePrice,
            cost,
            sellCost
        ) * convertingPrice
    )
}