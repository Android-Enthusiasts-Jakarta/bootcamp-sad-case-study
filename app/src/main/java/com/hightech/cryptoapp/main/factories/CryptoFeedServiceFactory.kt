package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.frameworks.HttpFactory
import com.hightech.cryptoapp.http.CryptoFeedService

class CryptoFeedServiceFactory {
    companion object {
        fun createCryptoFeedService(): CryptoFeedService {
            return HttpFactory.createRetrofit(
                HttpFactory.createMoshi(),
                HttpFactory.createOkhttpClient(
                    HttpFactory.createLoggingInterceptor()
                )
            ).create(CryptoFeedService::class.java)
        }
    }
}