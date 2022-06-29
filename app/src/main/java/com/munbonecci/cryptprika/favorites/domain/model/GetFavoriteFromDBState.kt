package com.munbonecci.cryptprika.favorites.domain.model

import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.database.favorites.Favorite

data class GetFavoriteFromDBState(
    var isLoading: Boolean = false,
    var getFavorite: Favorite = Favorite(),
    val error: Error? = null,
)
