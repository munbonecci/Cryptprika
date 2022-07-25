package com.munbonecci.cryptprika.features.paprika_detail.domain.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.paprika_detail.domain.model.CoinDetail

interface PaprikaDetailRepository {
    suspend fun getCoinById(coinId: String): Resource<CoinDetail>
}