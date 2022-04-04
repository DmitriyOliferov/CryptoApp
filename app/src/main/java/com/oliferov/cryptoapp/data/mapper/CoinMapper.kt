package com.oliferov.cryptoapp.data.mapper

import com.google.gson.Gson
import com.oliferov.cryptoapp.data.database.CoinInfoDbModel
import com.oliferov.cryptoapp.data.network.model.CoinInfoDto
import com.oliferov.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.oliferov.cryptoapp.data.network.model.CoinNamesListDto
import com.oliferov.cryptoapp.domain.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price.toString(),
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay.toString(),
        lowDay = dto.lowDay.toString(),
        lastMarket = dto.lastMarket,
        imageUrl = dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {

        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price.toString(),
        lastUpdate = dbModel.lastUpdate,
        highDay = dbModel.highDay.toString(),
        lowDay = dbModel.lowDay.toString(),
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )
}