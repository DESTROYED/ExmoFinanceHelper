package com.destr.financehelper.data.datasource.cloud

import com.destr.financehelper.data.datasource.cloud.response.PairDetail

interface ServerDao {

    suspend fun getCurrenciesAsync(): List<String>

    suspend fun getPairWithDetails(): Map<String, PairDetail>

}