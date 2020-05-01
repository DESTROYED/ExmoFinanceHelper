package com.destr.financehelper.presentation.screen.calcualte

import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CalculatePresenterTest {

    private val calculatePresenter = CalculatePresenter()
    private val view = mockk<CalculateView>(relaxUnitFun = true)

    private val emptyValue = .0

    // Entrance price value
    private val bigEntrancePrice = 5.0
    private val fractionalEntrancePrice = .5

    // Cost value
    private val costValue = .25

    // Sell cost value
    private val sellCostValue = .35

    // Converting price value
    private val bigConvertingPriceValue = 9000.0
    private val fractionalConvertingPriceValue = .5

    // Big values
    private val nullBestBigOutput = 0.250249875124844
    private val sellPriceBigOutput = 0.49875062437578
    private val convertingBigPrice = 4488.75561938202

    // Fractional values
    private val nullBestFractionalOutput = 0.250249875124844
    private val sellPriceFractionalOutput = 0.049875062437578
    private val convertingFractionalPriceOutput = 0.024937531218789

    @Nested
    @DisplayName("On cost text changed")
    inner class OnCostTextChangedTests {

        @Test
        fun `Calculate cost text changed with 0 values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onCostTextChanged(emptyValue, emptyValue, emptyValue, emptyValue)
            coVerify {
                with(view) {
                    hideBestPriceBlock()
                    hideProfitBlock()
                    hideConvertedBlock()
                }
            }
        }

        @Test
        fun `Calculate cost text changed with big values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onCostTextChanged(
                bigEntrancePrice,
                costValue,
                sellCostValue,
                bigConvertingPriceValue
            )
            coVerify {
                with(view) {
                    setCalculatedNullBestPrice(nullBestBigOutput)
                    setCalculatedSellCostPrice(sellPriceBigOutput)
                    setCalculatedConvertedPrice(convertingBigPrice)
                }
            }
        }

        @Test
        fun `Calculate cost text changed with fractional values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onCostTextChanged(
                fractionalEntrancePrice,
                costValue,
                sellCostValue,
                fractionalConvertingPriceValue
            )
            coVerify {
                with(view) {
                    setCalculatedNullBestPrice(nullBestFractionalOutput)
                    setCalculatedSellCostPrice(sellPriceFractionalOutput)
                    setCalculatedConvertedPrice(convertingFractionalPriceOutput)
                }
            }
        }
    }

    @Nested
    @DisplayName("On sell or entrance price text changed")
    inner class OnSellPriceTextChangedTest {
        @Test
        fun `Calculate sell or entrance price text changed with 0 values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onSellOrEntrancePriceTextChanged(
                emptyValue,
                emptyValue,
                emptyValue,
                emptyValue
            )
            coVerify {
                with(view) {
                    hideProfitBlock()
                    hideConvertedBlock()
                }
            }
        }

        @Test
        fun `Calculate sell or entrance price text changed with big values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onSellOrEntrancePriceTextChanged(
                bigEntrancePrice,
                costValue,
                sellCostValue,
                bigConvertingPriceValue
            )
            coVerify {
                with(view) {
                    setCalculatedSellCostPrice(sellPriceBigOutput)
                    setCalculatedConvertedPrice(convertingBigPrice)
                }
            }
        }

        @Test
        fun `Calculate sell or entrance price text changed with fractional values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onSellOrEntrancePriceTextChanged(
                fractionalEntrancePrice,
                costValue,
                sellCostValue,
                fractionalConvertingPriceValue
            )
            coVerify {
                with(view) {
                    setCalculatedSellCostPrice(sellPriceFractionalOutput)
                    setCalculatedConvertedPrice(convertingFractionalPriceOutput)
                }
            }
        }
    }

    @Nested
    @DisplayName("On converting course text changed")
    inner class OnConvertingCourseTextChangedTest {
        @Test
        fun `Calculate converting course text changed with 0 values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onSellOrEntrancePriceTextChanged(
                emptyValue,
                emptyValue,
                emptyValue,
                emptyValue
            )
            coVerify {
                with(view) {
                    hideConvertedBlock()
                }
            }
        }

        @Test
        fun `Calculate converting course text changed with big values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onConvertingCourseTextChanged(
                bigEntrancePrice,
                costValue,
                sellCostValue,
                bigConvertingPriceValue
            )
            verify {
                view.setCalculatedConvertedPrice(convertingBigPrice)
            }
        }

        @Test
        fun `Calculate converting course text changed with fractional values`() {
            calculatePresenter.attachView(view)
            calculatePresenter.onConvertingCourseTextChanged(
                fractionalEntrancePrice,
                costValue,
                sellCostValue,
                fractionalConvertingPriceValue
            )
            verify {
                view.setCalculatedConvertedPrice(convertingFractionalPriceOutput)
            }
        }
    }
}