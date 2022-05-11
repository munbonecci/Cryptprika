package com.munbonecci.cryptprika.paprika_list.domain.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.paprika_list.domain.model.Coin

interface PaprikaListRepository {
    suspend fun getCoins(): Resource<List<Coin>>
}