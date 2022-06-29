package com.munbonecci.cryptprika.favorites.domain.model

import com.munbonecci.cryptprika.common.Error

data class DeleteFavoriteFromDBState(
    var isLoading: Boolean = false,
    var isDeleted: Boolean = false,
    val error: Error? = null,
)
