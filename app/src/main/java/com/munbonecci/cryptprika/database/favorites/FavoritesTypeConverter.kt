package com.munbonecci.cryptprika.database.favorites

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FavoritesTypeConverter {
    @TypeConverter
    fun stringToListFavoritesEntry(catalog: String): ArrayList<Favorite> {
        val type: Type = object : TypeToken<ArrayList<Favorite?>?>() {}.type
        return Gson().fromJson(catalog, type)
    }

    @TypeConverter
    fun listFavoritesEntryToString(list: ArrayList<Favorite?>?): String? {
        val type = object : TypeToken<ArrayList<Favorite?>?>() {}.type
        return Gson().toJson(list, type)
    }
}