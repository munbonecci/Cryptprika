package com.munbonecci.cryptprika.paprika_list.data.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.common.ResponseHandler
import com.munbonecci.cryptprika.paprika_list.data.remote.dto.toCoin
import com.munbonecci.cryptprika.paprika_list.domain.model.Coin
import com.munbonecci.cryptprika.paprika_list.domain.repository.PaprikaListRepository
import javax.inject.Inject

class PaprikaListRepositoryImpl @Inject constructor(
    private val api: PaprikaListApi,
    private val responseHandler: ResponseHandler
) : PaprikaListRepository {

    override suspend fun getCoins(): Resource<List<Coin>> {
        return responseHandler {
            api.getCoins().map { coinDto -> coinDto.toCoin() }
        }
    }
}