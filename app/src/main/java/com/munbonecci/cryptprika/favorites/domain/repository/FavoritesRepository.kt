package com.munbonecci.cryptprika.favorites.domain.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorite

interface FavoritesRepository {
    suspend fun getFavoritesFromDB(): Resource<List<Favorite>>
    suspend fun getFavoriteFromDB(idFavorite: String): Resource<Favorite>
    suspend fun insertFavoriteToDB(favorite: Favorite): Resource<Unit>
    suspend fun deleteFavoriteFromDB(id: Int): Resource<Unit>
}