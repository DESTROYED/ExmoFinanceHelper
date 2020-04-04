package com.destr.financehelper.presentation.util.view

import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("0.00000000000")

fun TextView.setCalculated(double: Double) {
    text = decimalFormat.format(double)
}

inline fun TextView.onTextChanged(crossinline action: (text: CharSequence?) -> Unit) =
    doOnTextChanged { text, _, _, _ -> action(text) }