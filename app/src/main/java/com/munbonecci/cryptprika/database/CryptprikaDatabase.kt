package com.munbonecci.cryptprika.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.munbonecci.cryptprika.database.favorites.Favorite
import com.munbonecci.cryptprika.database.favorites.FavoritesDao
import com.munbonecci.cryptprika.database.favorites.FavoritesTypeConverter

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
@TypeConverters(FavoritesTypeConverter::class)
abstract class CryptprikaDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}