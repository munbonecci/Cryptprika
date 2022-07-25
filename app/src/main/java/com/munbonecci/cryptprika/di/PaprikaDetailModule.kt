package com.munbonecci.cryptprika.di

import com.munbonecci.cryptprika.features.paprika_detail.data.repository.PaprikaDetailApi
import com.munbonecci.cryptprika.features.paprika_detail.data.repository.PaprikaDetailRepositoryImpl
import com.munbonecci.cryptprika.features.paprika_detail.domain.repository.PaprikaDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaprikaDetailModule {

    companion object {
        @Provides
        @Singleton
        fun providePaprikaDetailApi(retrofit: Retrofit): PaprikaDetailApi {
            return retrofit.create(PaprikaDetailApi::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindPaprikaDetailRepository(
        coinRepositoryImpl: PaprikaDetailRepositoryImpl
    ): PaprikaDetailRepository
}