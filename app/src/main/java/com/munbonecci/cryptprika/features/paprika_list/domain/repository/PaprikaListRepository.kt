package com.munbonecci.cryptprika.features.paprika_list.domain.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.paprika_list.domain.model.Coin

interface PaprikaListRepository {
    suspend fun getCoins(): Resource<List<Coin>>
}