package com.destr.financehelper.presentation

import android.app.Application
import com.destr.financehelper.data.datasource.CurrencyPairFactory
import com.destr.financehelper.data.datasource.CurrencyPairRepositoryImpl
import com.destr.financehelper.domain.CurrencyPairRepository

class ExmoFinanceHelperApplication : Application() {

    val currencyPairRepository: CurrencyPairRepository by lazy { CurrencyPairRepositoryImpl }

    override fun onCreate() {
        super.onCreate()
        CurrencyPairRepositoryImpl.currencyPairFactory = CurrencyPairFactory(applicationContext)
    }
}