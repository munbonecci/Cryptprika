package com.munbonecci.cryptprika.favorites.domain.model

import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.database.favorites.Favorites

data class GetFavoritesFromDBState(
    var isLoading: Boolean = false,
    var getFavorites: List<Favorites> = emptyList(),
    val error: Error? = null,
)