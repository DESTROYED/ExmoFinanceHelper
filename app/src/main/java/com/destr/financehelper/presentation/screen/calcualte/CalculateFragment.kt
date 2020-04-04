package com.destr.financehelper.presentation.screen.calcualte

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.destr.financehelper.R
import com.destr.financehelper.domain.util.getDouble
import com.destr.financehelper.presentation.common.MvpNavFragment
import com.destr.financehelper.presentation.util.view.onTextChanged
import com.destr.financehelper.presentation.util.view.setCalculated
import kotlinx.android.synthetic.main.calculated_fragment.*
import kotlinx.android.synthetic.main.calculated_fragment.view.*
import moxy.presenter.InjectPresenter

class CalculateFragment : MvpNavFragment(),
    CalculateView {

    @InjectPresenter
    lateinit var calculatePresenter: CalculatePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calculated_fragment, container, false)
            .apply { initViews(this) }
    }

    private fun initViews(view: View) {
        with(view) {
            cost.onTextChanged {
                with(calculatePresenter) {
                    calculateNullBestPrice(cost.text.getDouble())
                    calculateSellCostPrice(
                        entrancePrice.text.getDouble(),
                        cost.text.getDouble(),
                        sellCost.text.getDouble()
                    )
                    calculatedConvertedPrice(
                        entrancePrice.text.getDouble(),
                        cost.text.getDouble(),
                        sellCost.text.getDouble(),
                        convertingCourse.text.getDouble()
                    )
                }
            }

            sellCost.onTextChanged {
                with(calculatePresenter) {
                    calculateSellCostPrice(
                        entrancePrice.text.getDouble(),
                        cost.text.getDouble(),
                        sellCost.text.getDouble()
                    )
                    calculatedConvertedPrice(
                        entrancePrice.text.getDouble(),
                        cost.text.getDouble(),
                        sellCost.text.getDouble(),
                        convertingCourse.text.getDouble()
                    )
                }

                entrancePrice.onTextChanged {
                    with(calculatePresenter) {
                        calculateSellCostPrice(
                            entrancePrice.text.getDouble(),
                            cost.text.getDouble(),
                            sellCost.text.getDouble()
                        )
                        calculatedConvertedPrice(
                            entrancePrice.text.getDouble(),
                            cost.text.getDouble(),
                            sellCost.text.getDouble(),
                            convertingCourse.text.getDouble()
                        )
                    }
                }

                convertingCourse.onTextChanged {
                    calculatePresenter.calculatedConvertedPrice(
                        entrancePrice.text.getDouble(),
                        cost.text.getDouble(),
                        sellCost.text.getDouble(),
                        convertingCourse.text.getDouble()
                    )
                }
            }
        }
    }

    override fun setCalculatedNullBestPrice(calculatedNullBestPrice: Double) =
        if (cost.text.getDouble() != .0) {
            bestPriceBlock.isVisible = true
            minimalBestPrice.setCalculated(calculatedNullBestPrice)
        } else {
            bestPriceBlock.isVisible = false
        }

    override fun setCalculatedSellCostPrice(calculatedSellCostPrice: Double) {
        if (isSellCostPriceValid()) {
            profitBlock.isVisible = true
            scalpedValue.setCalculated(calculatedSellCostPrice)
        } else {
            profitBlock.isVisible = false
        }
    }

    private fun isSellCostPriceValid(): Boolean {
        return entrancePrice.text.getDouble() != .0
                && cost.text.getDouble() != .0
                && sellCost.text.getDouble() != .0
    }

    override fun setCalculatedConvertedPrice(calculatedConvertedPrice: Double) {
        if (isConvertedPriceValid()) {
            convertedBlock.isVisible = true
            convertedPrice.setCalculated(calculatedConvertedPrice)
        } else {
            convertedBlock.isVisible = false
        }
    }

    private fun isConvertedPriceValid(): Boolean {
        return entrancePrice.text.getDouble() != .0
                && cost.text.getDouble() != .0
                && sellCost.text.getDouble() != .0
                && convertingCourse.text.getDouble() != .0
    }
}