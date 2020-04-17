package com.destr.financehelper.domain

import kotlin.math.sqrt

class CalculateEntity {
    companion object {
        private const val PERCENTAGE = 0.2
    }

    fun getCostDiff(entrancePrice: Double, cost: Double, sellCost: Double) =
        entrancePrice * (sellCost - getNotLossCost(cost))

    fun getNotLossCost(cost: Double) = cost * sqrt(getPercentage())

    private fun getPercentage() = 1 + .01 * PERCENTAGE
}