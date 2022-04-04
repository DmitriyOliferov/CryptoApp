package com.oliferov.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.oliferov.cryptoapp.data.repository.CoinRepositoryImpl
import com.oliferov.cryptoapp.domain.GetCoinInfoListUseCase
import com.oliferov.cryptoapp.domain.GetCoinInfoUseCase
import com.oliferov.cryptoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init{
        viewModelScope.launch {
            loadDataUseCase()
        }

    }


}