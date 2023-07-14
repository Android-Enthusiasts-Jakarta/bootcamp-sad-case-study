package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.domain.CryptoFeedLoader
import com.hightech.cryptoapp.crypto.feed.http.usecases.RemoteCryptoFeedLoader

class RemoteCryptoFeedLoaderFactory {
    companion object {
        fun createRemoteCryptoFeedLoader(): CryptoFeedLoader {
            return RemoteCryptoFeedLoader(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}