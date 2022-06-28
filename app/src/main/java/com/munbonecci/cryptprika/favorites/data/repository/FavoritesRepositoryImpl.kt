package com.munbonecci.cryptprika.favorites.data.repository

import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.common.ResponseHandler
import com.munbonecci.cryptprika.database.favorites.Favorite
import com.munbonecci.cryptprika.database.favorites.FavoritesDao
import com.munbonecci.cryptprika.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDatabase: FavoritesDao,
    private val responseHandler: ResponseHandler
) : FavoritesRepository {

    override suspend fun getFavoritesFromDB(): Resource<List<Favorite>> {
        return responseHandler {
            favoritesDatabase.getFavorites()
        }
    }

    override suspend fun getFavoriteFromDB(idFavorite: String): Resource<Favorite> {
        return responseHandler {
            favoritesDatabase.getFavoriteById(idFavorite)
        }
    }

    override suspend fun insertFavoriteToDB(favorite: Favorite): Resource<Unit> {
        return responseHandler {
            favoritesDatabase.insertFavorites(favorite)
        }
    }

    override suspend fun deleteFavoriteFromDB(id: Int): Resource<Unit> {
        return responseHandler {
            favoritesDatabase.deleteFavorite(id)
        }
    }

}