package com.destr.financehelper.presentation

import android.app.Application
import com.destr.financehelper.data.datasource.CurrencyPairFactory
import com.destr.financehelper.data.datasource.CurrencyPairRepositoryImpl

class ExmoFinanceHelperApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CurrencyPairRepositoryImpl.currencyPairFactory = CurrencyPairFactory(applicationContext)
    }
}