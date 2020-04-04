package com.destr.financehelper.domain.util

fun CharSequence?.getDouble(): Double {
    if (isNullOrEmpty() || toString() == ".") return .0
    return toString().toDouble()
}