package com.destr.financehelper.presentation.screen.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.destr.financehelper.R
import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.course_showcase_item.view.*

class CourseAdapter(diffCallback: DiffUtil.ItemCallback<Pair<String, PairDetail>>) :
    ListAdapter<Pair<String, PairDetail>, CourseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.course_showcase_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currency = getItem(position)
        with(holder) {
            currencyPair.text = currency.first.replace("_", "/")
            avgForDay.text = currency.second.avg
            buyPrice.text = currency.second.buyPrice
            sellPrice.text = currency.second.sellPrice
        }
    }
}

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val currencyPair: MaterialTextView = itemView.currencyPair
    val avgForDay: MaterialTextView = itemView.avgForDay
    val buyPrice: MaterialTextView = itemView.buyPrice
    val sellPrice: MaterialTextView = itemView.sellPrice
}
