package com.destr.financehelper.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(val currencyPair: String,val isFavorite: Boolean, @PrimaryKey(autoGenerate = true) val id: Int = 0)