package com.munbonecci.cryptprika.database.favorites

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FavoritesTypeConverter {
    @TypeConverter
    fun stringToListFavoritesEntry(catalog: String): ArrayList<Favorites> {
        val type: Type = object : TypeToken<ArrayList<Favorites?>?>() {}.type
        return Gson().fromJson(catalog, type)
    }

    @TypeConverter
    fun listFavoritesEntryToString(list: ArrayList<Favorites?>?): String? {
        val type = object : TypeToken<ArrayList<Favorites?>?>() {}.type
        return Gson().toJson(list, type)
    }
}