package com.munbonecci.cryptprika.features.ticker_detail.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.ticker_detail.domain.model.Ticker
import com.munbonecci.cryptprika.features.ticker_detail.domain.repository.TickerDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTickerDetailUseCase @Inject constructor(
    private val repository: TickerDetailRepository
) {
    operator fun invoke(tickerId: String): Flow<Resource<Ticker>> = flow {
        emit(repository.getTickerById(tickerId))
    }
}