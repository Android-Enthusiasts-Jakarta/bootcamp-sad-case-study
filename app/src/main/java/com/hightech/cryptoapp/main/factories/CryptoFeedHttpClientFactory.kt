package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.http.CryptoFeedHttpClient
import com.hightech.cryptoapp.crypto.feed.http.CryptoFeedRetrofitHttpClient

class CryptoFeedHttpClientFactory {
    companion object {
        fun createCryptoFeedHttpClient(): CryptoFeedHttpClient {
            return CryptoFeedRetrofitHttpClient(
                CryptoFeedServiceFactory.createCryptoFeedService()
            )
        }
    }
}