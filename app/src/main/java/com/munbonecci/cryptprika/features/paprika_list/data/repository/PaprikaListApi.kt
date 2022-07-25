package com.munbonecci.cryptprika.features.paprika_list.data.repository

import com.munbonecci.cryptprika.features.paprika_list.data.remote.dto.PaprikaDto
import retrofit2.http.GET

interface PaprikaListApi {
    @GET("v1/coins")
    suspend fun getCoins(): List<PaprikaDto>
}