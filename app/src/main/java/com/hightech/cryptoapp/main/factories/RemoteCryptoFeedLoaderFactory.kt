package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.domain.CryptoFeedLoader
import com.hightech.cryptoapp.http.usecases.RemoteCryptoFeedLoader

class RemoteCryptoFeedLoaderFactory {
    companion object {
        fun createRemoteCryptoFeedLoader(): CryptoFeedLoader {
            return RemoteCryptoFeedLoader(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}