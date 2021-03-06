package com.oliferov.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.oliferov.cryptoapp.data.database.AppDatabase
import com.oliferov.cryptoapp.data.database.CoinInfoDao
import com.oliferov.cryptoapp.data.database.CoinInfoDao_Impl
import com.oliferov.cryptoapp.data.mapper.CoinMapper
import com.oliferov.cryptoapp.data.network.ApiFactory
import com.oliferov.cryptoapp.data.workers.RefreshDataWorker
import com.oliferov.cryptoapp.domain.CoinInfo
import com.oliferov.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)){
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}