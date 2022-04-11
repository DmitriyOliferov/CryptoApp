package com.oliferov.cryptoapp.di

import android.app.Application
import com.oliferov.cryptoapp.data.database.AppDatabase
import com.oliferov.cryptoapp.data.database.CoinInfoDao
import com.oliferov.cryptoapp.data.repository.CoinRepositoryImpl
import com.oliferov.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}