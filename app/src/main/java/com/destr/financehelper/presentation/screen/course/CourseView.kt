package com.destr.financehelper.presentation.screen.course

import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import moxy.MvpView

interface CourseView: MvpView {

    fun setFirstCurrency(currencies : List<String>)

    fun setSecondCurrency(currencies : List<String>)

    fun setPairs(pairs : Map<String, PairDetail>)
}