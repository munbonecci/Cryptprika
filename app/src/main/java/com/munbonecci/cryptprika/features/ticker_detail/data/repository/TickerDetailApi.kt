package com.munbonecci.cryptprika.features.ticker_detail.data.repository

import com.munbonecci.cryptprika.features.ticker_detail.data.remote.TickerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TickerDetailApi {
    @GET("v1/ticker/{tickerId}")
    suspend fun getTickerById(@Path("tickerId") tickerId: String): TickerDto
}