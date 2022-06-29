package com.munbonecci.cryptprika.favorites.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteFromDBUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(id: String): Flow<Resource<Unit>> = flow {
        emit(favoritesRepository.deleteFavoriteFromDB(id))
    }
}