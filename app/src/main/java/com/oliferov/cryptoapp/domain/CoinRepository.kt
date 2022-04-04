package com.oliferov.cryptoapp.domain

import androidx.lifecycle.LiveData
import java.text.DateFormatSymbols

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>
}