package com.munbonecci.cryptprika.ticker_detail.data.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.common.ResponseHandler

import com.munbonecci.cryptprika.ticker_detail.data.remote.toTicker
import com.munbonecci.cryptprika.ticker_detail.domain.model.Ticker
import com.munbonecci.cryptprika.ticker_detail.domain.repository.TickerDetailRepository
import javax.inject.Inject

class TickerDetailRepositoryImpl @Inject constructor(
    private val api: TickerDetailApi,
    private val responseHandler: ResponseHandler
) : TickerDetailRepository {

    override suspend fun getTickerById(tickerId: String): Resource<Ticker> {
        return responseHandler {
            api.getTickerById(tickerId).toTicker()
        }
    }
}