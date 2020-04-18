package com.destr.apierrorhandler

import android.content.Context
import com.destr.apierrorhandler.exception.InternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response


class ErrorInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isConnected()) throw InternetConnectionException
        val request = chain.request()
        val response = chain.proceed(request)
        return response.orThrow()
    }
}