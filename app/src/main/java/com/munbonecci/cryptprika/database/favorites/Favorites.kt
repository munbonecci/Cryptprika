package com.munbonecci.cryptprika.database.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites(
                     var id: String = "",
                     var image: String = "",
                     var name: String = "",
                     var creationDate: String = "",
                     var modificationDate: String = "",
                     var isFavoriteAdded: Boolean = false,
                     @PrimaryKey(autoGenerate = true)
                     var uuid: Int = 0
)
