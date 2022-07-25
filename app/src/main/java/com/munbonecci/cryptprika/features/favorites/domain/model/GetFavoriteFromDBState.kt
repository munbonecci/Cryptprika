package com.munbonecci.cryptprika.features.favorites.domain.model

import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.database.favorites.Favorite

data class GetFavoriteFromDBState(
    var isLoading: Boolean = false,
    var getFavorite: Favorite = Favorite(),
    val error: Error? = null,
)
