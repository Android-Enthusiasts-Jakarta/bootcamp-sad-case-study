package com.hightech.cryptoapp.crypto.feed.di

import com.hightech.cryptoapp.crypto.feed.presentation.CryptoFeedViewModel
import com.hightech.cryptoapp.main.factories.CryptoFeedHttpClientFactory
import com.hightech.cryptoapp.main.factories.CryptoFeedServiceFactory
import com.hightech.cryptoapp.main.factories.FeatureScope
import com.hightech.cryptoapp.main.factories.RemoteCryptoFeedLoaderFactory
import dagger.Subcomponent

@FeatureScope
@Subcomponent(
    modules = [
        RemoteCryptoFeedLoaderFactory::class,
        CryptoFeedHttpClientFactory::class,
        CryptoFeedServiceFactory::class
    ]
)
interface CryptoFeedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CryptoFeedComponent
    }

    fun cryptoFeedViewModel(): CryptoFeedViewModel
}