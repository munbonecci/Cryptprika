package com.munbonecci.cryptprika.di

import com.munbonecci.cryptprika.common.ResponseHandler
import com.munbonecci.cryptprika.database.cypher.DataBaseManager
import com.munbonecci.cryptprika.database.favorites.FavoritesDao
import com.munbonecci.cryptprika.favorites.data.repository.FavoritesRepositoryImpl
import com.munbonecci.cryptprika.favorites.domain.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesModule {

    @Provides
    @Singleton
    fun providesFavoritesDB(): FavoritesDao = DataBaseManager.favoritesDao

    @Provides
    @Singleton
    fun providesFavoritesRepository(responseHandler: ResponseHandler, favoritesDB: FavoritesDao):
            FavoritesRepository = FavoritesRepositoryImpl(favoritesDB, responseHandler)

}