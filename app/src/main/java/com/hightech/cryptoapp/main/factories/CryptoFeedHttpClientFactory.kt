package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.http.CryptoFeedHttpClient
import com.hightech.cryptoapp.crypto.feed.http.CryptoFeedRetrofitHttpClient
import dagger.Binds
import dagger.Module

@Module
abstract class CryptoFeedHttpClientFactory {
    @Binds
    abstract fun createCryptoFeedHttpClient(
        cryptoFeedRetrofitHttpClient: CryptoFeedRetrofitHttpClient
    ): CryptoFeedHttpClient
}