package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.http.CryptoFeedRetrofitHttpClient
import com.hightech.cryptoapp.http.CryptoFeedHttpClient

class CryptoFeedHttpClientFactory {
    companion object {
        fun createCryptoFeedHttpClient(): CryptoFeedHttpClient {
            return CryptoFeedRetrofitHttpClient(
                CryptoFeedServiceFactory.createCryptoFeedService()
            )
        }
    }
}