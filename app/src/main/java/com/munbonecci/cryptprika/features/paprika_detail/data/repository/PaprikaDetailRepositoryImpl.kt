package com.munbonecci.cryptprika.features.paprika_detail.data.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.common.ResponseHandler
import com.munbonecci.cryptprika.features.paprika_detail.data.remote.toCoinDetail
import com.munbonecci.cryptprika.features.paprika_detail.domain.model.CoinDetail
import com.munbonecci.cryptprika.features.paprika_detail.domain.repository.PaprikaDetailRepository
import javax.inject.Inject

class PaprikaDetailRepositoryImpl @Inject constructor(
    private val api: PaprikaDetailApi,
    private val responseHandler: ResponseHandler
) : PaprikaDetailRepository {

    override suspend fun getCoinById(coinId: String): Resource<CoinDetail> {
        return responseHandler {
            api.getCoinById(coinId).toCoinDetail()
        }
    }
}