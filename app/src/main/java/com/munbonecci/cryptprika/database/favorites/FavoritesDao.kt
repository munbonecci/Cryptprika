package com.munbonecci.cryptprika.database.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Insert
    suspend fun insertFavorites(favorites: Favorites): Long

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<Favorites>

    @Query("SELECT * FROM favorites WHERE id = :idFavorite")
    suspend fun getFavoriteById(idFavorite: String): List<Favorites>

    @Query("DELETE FROM favorites WHERE uuid = :id")
    suspend fun deleteFavorite(id: Int): Int
}