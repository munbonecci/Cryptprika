package com.munbonecci.cryptprika.features.paprika_list.domain.model

data class Coin(
    val id: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String,
    val isFavoriteSelected: Boolean
)