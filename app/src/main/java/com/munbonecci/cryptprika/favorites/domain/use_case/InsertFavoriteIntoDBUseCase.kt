package com.munbonecci.cryptprika.favorites.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorites
import com.munbonecci.cryptprika.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertFavoriteIntoDBUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(favorite: Favorites): Flow<Resource<Unit>> = flow {
        emit(favoritesRepository.insertFavoriteToDB(favorite))
    }
}