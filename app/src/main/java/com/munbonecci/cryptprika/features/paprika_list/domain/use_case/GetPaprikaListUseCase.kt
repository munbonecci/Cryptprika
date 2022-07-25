package com.munbonecci.cryptprika.features.paprika_list.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.paprika_list.domain.model.Coin
import com.munbonecci.cryptprika.features.paprika_list.domain.repository.PaprikaListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaprikaListUseCase @Inject constructor(
    private val repository: PaprikaListRepository
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(repository.getCoins())
    }
}