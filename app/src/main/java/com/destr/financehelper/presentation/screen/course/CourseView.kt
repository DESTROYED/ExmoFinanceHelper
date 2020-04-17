package com.destr.financehelper.presentation.screen.course

import com.destr.financehelper.domain.CurrencyPair
import moxy.MvpView

interface CourseView : MvpView {

    fun setFirstCurrency(currencies: List<String>)

    fun setSecondCurrency(currencies: List<String>)

    fun setPairs(pairs: List<CurrencyPair>?)
}