package com.munbonecci.cryptprika.features.favorites.domain.model

import com.munbonecci.cryptprika.common.Error

data class InsertFavoriteIntoDBState(
    var isLoading: Boolean = false,
    var isInserted: Boolean = false,
    val error: Error? = null,
)