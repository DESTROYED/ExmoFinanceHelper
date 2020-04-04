package com.destr.financehelper.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.destr.financehelper.data.datasource.cloud.response.PairDetail

class TaskDiffCallback : DiffUtil.ItemCallback<Pair<String, PairDetail>>() {

    override fun areItemsTheSame(
        oldItem: Pair<String, PairDetail>,
        newItem: Pair<String, PairDetail>
    ) = oldItem.first == newItem.first

    override fun areContentsTheSame(
        oldItem: Pair<String, PairDetail>, newItem: Pair<String, PairDetail>
    ) = oldItem == newItem
}