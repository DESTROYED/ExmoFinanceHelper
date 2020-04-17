package com.destr.financehelper.data.datasource.local

import androidx.room.*

@Dao
abstract class FavoriteDao {

    @Query("SELECT * FROM favorite_table WHERE isFavorite = 1")
    abstract fun getAllCurrencyFavoritePairs(): List<Favorite>

    @Query("UPDATE favorite_table SET isFavorite = :isFavorite WHERE currencyPair = :currencyPair ")
    abstract fun updateFavoriteState(currencyPair: String, isFavorite: Boolean)

    @Query("SELECT * FROM favorite_table WHERE currencyPair = :currencyPair")
    abstract fun getCurrencyPair(currencyPair: String): Favorite?

    @Insert
    abstract fun addCurrencyPair(favorite: Favorite)

    fun insertOrUpdatePair(item: Favorite) {
        val itemsFromDB = getCurrencyPair(item.currencyPair)
        if (itemsFromDB == null) addCurrencyPair(item)
        else updateFavoriteState(item.currencyPair, item.isFavorite)
    }

    @Delete
    abstract fun deleteCurrencyPair(favorite: Favorite)
}