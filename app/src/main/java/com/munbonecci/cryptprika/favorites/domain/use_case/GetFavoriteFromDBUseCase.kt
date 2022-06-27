package com.munbonecci.cryptprika.favorites.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorites
import com.munbonecci.cryptprika.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteFromDBUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(id: String): Flow<Resource<List<Favorites>>> = flow {
        emit(favoritesRepository.getFavoriteFromDB(id))
    }
}