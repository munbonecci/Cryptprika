package com.munbonecci.cryptprika.features.paprika_detail.data.repository

import com.munbonecci.cryptprika.features.paprika_detail.data.remote.PaprikaDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PaprikaDetailApi {
    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): PaprikaDetailDto
}
