package com.destr.financehelper.data.datasource

import android.content.Context
import com.destr.financehelper.data.datasource.cloud.CloudCurrencyPairStore
import com.destr.financehelper.data.datasource.local.LocalCurrencyPairStore

class CurrencyPairFactory(private val context: Context) {

    fun createCloudPairStorage() = CloudCurrencyPairStore()

    fun createLocalPairStorage() = LocalCurrencyPairStore(context)
}