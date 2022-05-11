package com.munbonecci.cryptprika.paprika_detail.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.paprika_detail.domain.model.CoinDetail
import com.munbonecci.cryptprika.paprika_detail.domain.repository.PaprikaDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaprikaDetailUseCase @Inject constructor(
    private val repository: PaprikaDetailRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        emit(repository.getCoinById(coinId))
    }
}