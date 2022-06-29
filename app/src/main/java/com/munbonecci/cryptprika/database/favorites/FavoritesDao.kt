package com.munbonecci.cryptprika.database.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Insert
    suspend fun insertFavorites(favorite: Favorite): Long

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<Favorite>

    @Query("SELECT * FROM favorites WHERE id = :idFavorite")
    suspend fun getFavoriteById(idFavorite: String): Favorite

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFavorite(id: String): Int
}