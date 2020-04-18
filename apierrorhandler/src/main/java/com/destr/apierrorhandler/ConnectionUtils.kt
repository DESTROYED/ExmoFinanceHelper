package com.destr.apierrorhandler

import android.content.Context
import android.net.ConnectivityManager

fun Context.isConnected() =
    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
