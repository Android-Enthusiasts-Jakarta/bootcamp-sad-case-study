package com.hightech.cryptoapp.main.factories

import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface MainComponent {
    @Component.Factory
    interface Factory {
        fun create(): MainComponent
    }
}