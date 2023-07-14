package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.presentation.CryptoFeedViewModel
import com.hightech.cryptoapp.frameworks.HttpFactory
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RemoteCryptoFeedLoaderFactory::class,
        CryptoFeedHttpClientFactory::class,
        CryptoFeedServiceFactory::class,
        HttpFactory::class
    ]
)
@Singleton
interface MainComponent {
    @Component.Factory
    interface Factory {
        fun create(): MainComponent
    }

     fun cryptoFeedViewModel(): CryptoFeedViewModel
}