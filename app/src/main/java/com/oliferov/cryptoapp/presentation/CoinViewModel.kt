package com.oliferov.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliferov.cryptoapp.data.repository.CoinRepositoryImpl
import com.oliferov.cryptoapp.domain.GetCoinInfoListUseCase
import com.oliferov.cryptoapp.domain.GetCoinInfoUseCase
import com.oliferov.cryptoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        loadDataUseCase()
    }


}