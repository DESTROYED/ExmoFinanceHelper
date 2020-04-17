package com.destr.financehelper.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.destr.financehelper.domain.CurrencyPair

class TaskDiffCallback : DiffUtil.ItemCallback<CurrencyPair>() {

    override fun areItemsTheSame(
        oldItem: CurrencyPair,
        newItem: CurrencyPair
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: CurrencyPair, newItem: CurrencyPair
    ) = oldItem == newItem
}