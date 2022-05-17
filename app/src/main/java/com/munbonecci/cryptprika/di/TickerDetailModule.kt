package com.munbonecci.cryptprika.di

import com.munbonecci.cryptprika.ticker_detail.data.repository.TickerDetailApi
import com.munbonecci.cryptprika.ticker_detail.data.repository.TickerDetailRepositoryImpl
import com.munbonecci.cryptprika.ticker_detail.domain.repository.TickerDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TickerDetailModule {

    companion object {
        @Provides
        @Singleton
        fun provideTickerDetailApi(retrofit: Retrofit): TickerDetailApi {
            return retrofit.create(TickerDetailApi::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindTickerDetailRepository(
        tickerDetailRepositoryImpl: TickerDetailRepositoryImpl
    ): TickerDetailRepository
}