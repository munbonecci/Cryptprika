package com.munbonecci.cryptprika.favorites.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorite
import com.munbonecci.cryptprika.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesFromDBUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(): Flow<Resource<List<Favorite>>> = flow {
        emit(favoritesRepository.getFavoritesFromDB())
    }
}