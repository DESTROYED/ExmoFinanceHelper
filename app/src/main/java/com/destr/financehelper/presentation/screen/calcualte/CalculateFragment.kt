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

internal class CalculateFragment : MvpNavFragment(),
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
                calculatePresenter.onCostTextChanged(
                    entrancePrice.text.getDouble(),
                    cost.text.getDouble(),
                    sellCost.text.getDouble(),
                    convertingCourse.text.getDouble()
                )
            }

            sellCost.onTextChanged {
                calculatePresenter.onSellOrEntrancePriceTextChanged(
                    entrancePrice.text.getDouble(),
                    cost.text.getDouble(),
                    sellCost.text.getDouble(),
                    convertingCourse.text.getDouble()
                )
            }

            entrancePrice.onTextChanged {
                calculatePresenter.onSellOrEntrancePriceTextChanged(
                    entrancePrice.text.getDouble(),
                    cost.text.getDouble(),
                    sellCost.text.getDouble(),
                    convertingCourse.text.getDouble()
                )
            }

            convertingCourse.onTextChanged {
                calculatePresenter.onConvertingCourseTextChanged(
                    entrancePrice.text.getDouble(),
                    cost.text.getDouble(),
                    sellCost.text.getDouble(),
                    convertingCourse.text.getDouble()
                )
            }
        }
    }

    override fun hideBestPriceBlock() {
        bestPriceBlock.isVisible = false
    }

    override fun hideProfitBlock() {
        profitBlock.isVisible = false
    }

    override fun hideConvertedBlock() {
        convertedBlock.isVisible = false
    }

    override fun setCalculatedNullBestPrice(calculatedNullBestPrice: Double) {
        bestPriceBlock.isVisible = true
        minimalBestPrice.setCalculated(calculatedNullBestPrice)
    }

    override fun setCalculatedSellCostPrice(calculatedSellCostPrice: Double) {
        profitBlock.isVisible = true
        scalpedValue.setCalculated(calculatedSellCostPrice)
    }

    override fun setCalculatedConvertedPrice(calculatedConvertedPrice: Double) {
        convertedBlock.isVisible = true
        convertedPrice.setCalculated(calculatedConvertedPrice)
    }
}