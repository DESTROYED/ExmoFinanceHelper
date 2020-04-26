package com.destr.financehelper.presentation.util

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.destr.financehelper.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


fun View.showInfoSnackbar(
    message: String,
    isError: Boolean = true
) {
    Snackbar.make(
        this, message, Snackbar.LENGTH_SHORT
    ).apply {
        view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams).apply {
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        }

        animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE

        setBackgroundTint(
            if (isError) ContextCompat.getColor(context, R.color.colorError)
            else ContextCompat.getColor(context, R.color.colorInfo)
        )

        setTextColor(ContextCompat.getColor(context, R.color.colorText))

        show()
    }
}