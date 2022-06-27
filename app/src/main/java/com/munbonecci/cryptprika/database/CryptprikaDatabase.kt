package com.munbonecci.cryptprika.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.munbonecci.cryptprika.database.favorites.Favorites
import com.munbonecci.cryptprika.database.favorites.FavoritesDao

@Database(entities = [Favorites::class], version = 1, exportSchema = false)
@TypeConverters
abstract class CryptprikaDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}