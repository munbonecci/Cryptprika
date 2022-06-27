package com.munbonecci.cryptprika.favorites.domain.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorites

interface FavoritesRepository {
    suspend fun getFavoritesFromDB(): Resource<List<Favorites>>
    suspend fun getFavoriteFromDB(idFavorite: String): Resource<List<Favorites>>
    suspend fun insertFavoriteToDB(favorite: Favorites): Resource<Unit>
    suspend fun deleteFavoriteFromDB(id: Int): Resource<Unit>
}