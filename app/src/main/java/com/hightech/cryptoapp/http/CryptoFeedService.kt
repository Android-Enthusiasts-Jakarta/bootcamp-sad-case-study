package com.hightech.cryptoapp.http

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoFeedService {
    @GET("data/top/totaltoptiervolfull")
    suspend fun get(
        @Query("limit") limit: Int? = 20,
        @Query("tsym") tsym: String? = "USD"
    ): RemoteRootCryptoFeed
}