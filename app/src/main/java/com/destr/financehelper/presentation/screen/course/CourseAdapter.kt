package com.destr.financehelper.presentation.screen.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.destr.financehelper.R
import com.destr.financehelper.domain.CurrencyPair
import com.destr.financehelper.domain.getImageByCoinName
import java.util.*

class CourseAdapter(
    diffCallback: DiffUtil.ItemCallback<CurrencyPair>,
    private val onPairLongClicked: (CurrencyPair) -> Unit
) : ListAdapter<CurrencyPair, CourseViewHolder>(diffCallback) {

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
                    currency.firstCurrency.toLowerCase(Locale.ROOT)
                )
            )
            secondCurrencyIcon.rotation = -15f
            secondCurrencyIcon.setImageDrawable(
                getImageByCoinName(
                    firstCurrencyIcon.context,
                    currency.secondCurrency.toLowerCase(Locale.ROOT)
                )
            )
            currencyPair.text = currency?.pairName
            avgForDay.text = currency.avg
            if (currency.isFavorite) {
                rootView.setBackgroundColor(rootView.context.getColor(R.color.colorBackgroundFavorite))
            } else {
                rootView.setBackgroundColor(rootView.context.getColor(R.color.colorSpinnerBackground))
            }

            itemView.setOnLongClickListener {
                onPairLongClicked.invoke(currency)
                currency.isFavorite = !currency.isFavorite
                notifyItemChanged(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }
}