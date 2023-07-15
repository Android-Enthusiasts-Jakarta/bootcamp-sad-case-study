package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.di.CryptoFeedComponent
import com.hightech.cryptoapp.frameworks.HttpFactory
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        HttpFactory::class,
        MainSubcomponents::class
    ]
)
@Singleton
interface MainComponent {
    @Component.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun cryptoFeedComponent(): CryptoFeedComponent.Factory
}