package com.munbonecci.cryptprika.features.favorites.domain.use_case

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorite
import com.munbonecci.cryptprika.features.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertFavoriteIntoDBUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(favorite: Favorite): Flow<Resource<Unit>> = flow {
        emit(favoritesRepository.insertFavoriteToDB(favorite))
    }
}