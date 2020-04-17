package com.destr.financehelper.data.datasource.local

import androidx.room.*

@Dao
abstract class FavoriteDao {

    @Query("SELECT currencyPair FROM favorite_table WHERE isFavorite = 1 ORDER BY currencyPair ASC")
    abstract fun getAllCurrencyFavoritePairs(): List<String>

    @Update
    abstract fun updateFavoriteState(favorite: Favorite)

    @Insert
    abstract fun addCurrencyPair(favorite: Favorite)

    @Delete
    abstract fun deleteCurrencyPair(favorite: Favorite)
}