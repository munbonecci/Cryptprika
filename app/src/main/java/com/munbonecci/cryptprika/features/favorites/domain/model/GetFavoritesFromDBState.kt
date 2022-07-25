package com.munbonecci.cryptprika.features.favorites.domain.model

import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.database.favorites.Favorite

data class GetFavoritesFromDBState(
    var isLoading: Boolean = false,
    var getFavorites: List<Favorite>?,
    val error: Error? = null,
)