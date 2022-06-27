package com.munbonecci.cryptprika.database.cypher

import android.content.Context
import android.content.SharedPreferences
import java.util.*

object PassUtils {
    private var uniqueID: String? = null
    private const val PREF_DB_KEY = "PREF_DB_KEY"

    @Synchronized
    fun getUuid(context: Context): String {
        return uniqueID ?: run {
            val sharedPrefs: SharedPreferences = context.getSharedPreferences(
                PREF_DB_KEY, Context.MODE_PRIVATE)

            sharedPrefs.getString(PREF_DB_KEY, null)?.also {
                uniqueID = it
            } ?: run {
                val newUniqueID: String = UUID.randomUUID().toString()
                val editor: SharedPreferences.Editor = sharedPrefs.edit()
                editor.putString(PREF_DB_KEY, newUniqueID)
                editor.apply()
                newUniqueID.also {
                    uniqueID = it
                }
            }
        }
    }
}