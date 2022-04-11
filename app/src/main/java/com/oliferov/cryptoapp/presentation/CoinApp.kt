package com.oliferov.cryptoapp.presentation

import android.app.Application
import com.oliferov.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}