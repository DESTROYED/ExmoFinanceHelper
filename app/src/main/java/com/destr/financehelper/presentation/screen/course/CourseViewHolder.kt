package com.destr.financehelper.presentation.screen.course

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.course_showcase_item.view.*

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val currencyPair: MaterialTextView = itemView.currencyPair
    val avgForDay: MaterialTextView = itemView.avgForDay
    val rootView: View = itemView.itemBackground
    val firstCurrencyIcon: ImageView = itemView.firstCurrencyIcon
    val secondCurrencyIcon: ImageView = itemView.secondCurrencyIcon
}