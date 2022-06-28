package com.munbonecci.cryptprika.favorites.domain.model

import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.database.favorites.Favorite

data class GetFavoritesFromDBState(
    var isLoading: Boolean = false,
    var getFavorites: List<Favorite> = emptyList(),
    val error: Error? = null,
)