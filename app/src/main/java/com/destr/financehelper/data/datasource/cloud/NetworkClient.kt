package com.destr.financehelper.data.datasource.cloud

import com.destr.apierrorhandler.ErrorInterceptor
import com.destr.financehelper.BuildConfig
import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.destr.financehelper.data.Api
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {

    private val api by lazy { retrofit.create(Api::class.java) }
    var interceptor: ErrorInterceptor? = null

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        object : TypeToken<Map<String, PairDetail>>() {}.type,
                        JsonMapDeserializer()
                    ).create()
                )
            )
            .build()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .apply {
                interceptor?.let { addInterceptor(it) }
                if (BuildConfig.DEBUG) addInterceptor(setupHttpLoggingInterceptor())
            }
            .retryOnConnectionFailure(true)
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            .build()
    }

    private fun setupHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    suspend fun getCurrenciesAsync() = api.getCurrenciesAsync()

    suspend fun getPairWithDetails() = api.getPairWithDetails()
}