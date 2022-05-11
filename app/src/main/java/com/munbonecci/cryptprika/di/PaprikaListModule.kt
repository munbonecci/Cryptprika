package com.munbonecci.cryptprika.di

import com.munbonecci.cryptprika.paprika_list.data.repository.PaprikaListApi
import com.munbonecci.cryptprika.paprika_list.data.repository.PaprikaListRepositoryImpl
import com.munbonecci.cryptprika.paprika_list.domain.repository.PaprikaListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaprikaListModule {

    companion object {
        @Provides
        @Singleton
        fun providePaprikaListApi(retrofit: Retrofit): PaprikaListApi {
            return retrofit.create(PaprikaListApi::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindPaprikaListRepository(
        coinRepositoryImpl: PaprikaListRepositoryImpl
    ): PaprikaListRepository
}