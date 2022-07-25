package com.munbonecci.cryptprika.features.ticker_detail.data.remote

import com.google.gson.annotations.SerializedName
import com.munbonecci.cryptprika.features.ticker_detail.domain.model.Ticker

data class TickerDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    @SerializedName("price_usd")
    val priceUsd: String,
    @SerializedName("price_btc")
    val priceBtc: String,
    @SerializedName("volume_24h_usd")
    val volume24hUsd: String,
    @SerializedName("market_cap_usd")
    val marketCapUsd: String,
    @SerializedName("circulating_supply")
    val circulatingSupply: String,
    @SerializedName("total_supply")
    val totalSupply: String,
    @SerializedName("max_supply")
    val maxSupply: String,
    @SerializedName("percent_change_1h")
    val percentChange1h: String,
    @SerializedName("percent_change_24h")
    val percentChange24h: String,
    @SerializedName("percent_change_7d")
    val percentChange7d: String,
    @SerializedName("last_updated")
    val lastUpdated: String
) {
    override fun toString(): String = id
}

fun TickerDto.toTicker(): Ticker {
    return Ticker(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd,
        priceBtc = priceBtc,
        marketCapUsd = marketCapUsd,
        circulatingSupply = circulatingSupply,
        totalSupply = totalSupply,
        maxSupply = maxSupply,
        percentChange1h = percentChange1h,
        percentChange24h = percentChange24h,
        percentChange7d = percentChange7d,
        lastUpdated = lastUpdated
    )
}