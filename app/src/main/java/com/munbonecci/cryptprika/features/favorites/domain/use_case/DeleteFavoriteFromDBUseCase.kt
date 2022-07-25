package com.munbonecci.cryptprika.features.favorites.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteFromDBUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(id: String): Flow<Resource<Unit>> = flow {
        emit(favoritesRepository.deleteFavoriteFromDB(id))
    }
}