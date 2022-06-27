package com.munbonecci.cryptprika.favorites.domain.model

import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.database.favorites.Favorites

data class GetFavoriteFromDBState(
    var isLoading: Boolean = false,
    var getFavorite: Favorites? = null,
    val error: Error? = null,
)
