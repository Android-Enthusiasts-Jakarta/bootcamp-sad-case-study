package com.hightech.cryptoapp.main.factories

import android.app.Application

class MainApplication: Application() {
    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.factory().create()
    }
}