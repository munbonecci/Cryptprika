package com.munbonecci.cryptprika.features.ticker_detail.domain.model

data class Ticker(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    val priceUsd: String,
    val priceBtc: String,
    val marketCapUsd: String,
    val circulatingSupply: String,
    val totalSupply: String,
    val maxSupply: String,
    val percentChange1h: String,
    val percentChange24h: String,
    val percentChange7d: String,
    val lastUpdated: String
) {
    override fun toString(): String = id
}