package com.oliferov.cryptoapp.di

import android.app.Application
import com.oliferov.cryptoapp.presentation.CoinDetailFragment
import com.oliferov.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)
}