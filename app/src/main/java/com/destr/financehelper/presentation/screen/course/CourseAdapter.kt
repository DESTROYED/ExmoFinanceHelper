package com.destr.financehelper.presentation.screen.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.destr.financehelper.R
import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.destr.financehelper.domain.getImageByCoinName
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.course_showcase_item.view.*
import java.util.*

class CourseAdapter(
    diffCallback: DiffUtil.ItemCallback<Pair<String, PairDetail>>,
    private val onPairLongClicked: (Int, String) -> Unit
    ) : ListAdapter<Pair<String, PairDetail>, CourseViewHolder>(diffCallback) {

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
            firstCurrencyIcon.rotation = 15f
            firstCurrencyIcon.setImageDrawable(
                getImageByCoinName(
                    firstCurrencyIcon.context,
                    currency.first.split("_")[0].toLowerCase(Locale.ROOT)
                )
            )
            secondCurrencyIcon.rotation = -15f
            secondCurrencyIcon.setImageDrawable(
                getImageByCoinName(
                    firstCurrencyIcon.context,
                    currency.first.split("_")[1].toLowerCase(Locale.ROOT)
                )
            )
            currencyPair.text = currency.first.replace("_", "/")
            avgForDay.text = currency.second.avg
            itemView.setOnLongClickListener {
                onPairLongClicked.invoke(adapterPosition, currency.first)
                return@setOnLongClickListener true
            }
        }
    }
}

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val currencyPair: MaterialTextView = itemView.currencyPair
    val avgForDay: MaterialTextView = itemView.avgForDay
    val firstCurrencyIcon: ImageView = itemView.firstCurrencyIcon
    val secondCurrencyIcon: ImageView = itemView.secondCurrencyIcon
}
