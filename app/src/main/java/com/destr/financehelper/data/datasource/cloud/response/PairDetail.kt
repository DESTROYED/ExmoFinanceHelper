package com.destr.financehelper.data.datasource.cloud.response

import com.google.gson.annotations.SerializedName

data class PairDetail(
    @SerializedName("avg")
    val avg: String,
    @SerializedName("buy_price")
    val buyPrice: String,
    @SerializedName("high")
    val high: String,
    @SerializedName("last_trade")
    val lastTrade: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("sell_price")
    val sellPrice: String,
    @SerializedName("updated")
    val updated: Int,
    @SerializedName("vol")
    val vol: String,
    @SerializedName("vol_curr")
    val volCurr: String
)