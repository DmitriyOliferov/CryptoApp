package com.oliferov.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.oliferov.cryptoapp.R
import com.oliferov.cryptoapp.data.network.model.CoinInfoDto
import com.oliferov.cryptoapp.presentation.adapters.CoinInfoAdapter
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfoDto) {
                startActivity(
                    CoinDetailActivity.newIntent(
                        this@CoinPriceListActivity,
                        coinPriceInfo.fromSymbol
                    )
                )
            }
        }
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinPriceInfoList = it
        })

    }
}