package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.domain.CryptoFeedLoader
import com.hightech.cryptoapp.crypto.feed.http.usecases.RemoteCryptoFeedLoader
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteCryptoFeedLoaderFactory {
    @Binds
    abstract fun createRemoteCryptoFeedLoader(
        remoteCryptoFeedLoader: RemoteCryptoFeedLoader
    ): CryptoFeedLoader
}