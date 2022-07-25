package com.munbonecci.cryptprika.features.ticker_detail.domain.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.ticker_detail.domain.model.Ticker

interface TickerDetailRepository {
    suspend fun getTickerById(tickerId: String): Resource<Ticker>
}