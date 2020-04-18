package com.destr.financehelper.presentation

import android.app.Application
import com.destr.apierrorhandler.ApiUncaughtExceptionHandler
import com.destr.apierrorhandler.ErrorInterceptor
import com.destr.financehelper.data.datasource.CurrencyPairFactory
import com.destr.financehelper.data.datasource.CurrencyPairRepositoryImpl
import com.destr.financehelper.data.datasource.cloud.NetworkClient
import com.destr.financehelper.domain.CurrencyPairRepository
import com.destr.financehelper.presentation.common.ApiErrorsHandler

class ExmoFinanceHelperApplication : Application() {

    val currencyPairRepository: CurrencyPairRepository by lazy { CurrencyPairRepositoryImpl }

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler(ApiUncaughtExceptionHandler(ApiErrorsHandler))
        NetworkClient.interceptor = ErrorInterceptor(applicationContext)
        CurrencyPairRepositoryImpl.currencyPairFactory = CurrencyPairFactory(applicationContext)
    }
}