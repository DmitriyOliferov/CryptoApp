package com.oliferov.cryptoapp.data.network

import com.oliferov.cryptoapp.data.network.model.CoinNamesListDto
import com.oliferov.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET ("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = MY_API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = MY_API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSym: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSym: String = CURRENCY
    ): CoinInfoJsonContainerDto

    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
        private const val MY_API_KEY = "51562dd33be97141d4f8fc1f18765a4c2800fb35b2fb798af27c7fae6153c411"

    }
}