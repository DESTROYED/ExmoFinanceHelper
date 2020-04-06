package com.destr.financehelper.domain

import android.content.Context
import android.graphics.drawable.Drawable

fun getImageByCoinName(context: Context, coinName: String): Drawable =
    with(context) {
        var id = resources.getIdentifier(coinName, "drawable", packageName)
        if (id == 0) {
            id = resources.getIdentifier("logo", "drawable", packageName)
        }
        resources.getDrawable(id, null)
    }
