package com.destr.financehelper.data

import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import retrofit2.http.GET

interface Api {

    @GET("currency")
    suspend fun getCurrenciesAsync(): List<String>

    @GET("ticker")
    suspend fun getPairWithDetails(): Map<String, PairDetail>
}



