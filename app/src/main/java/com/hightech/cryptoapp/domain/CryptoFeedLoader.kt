<<<<<<<< HEAD:app/src/main/java/com/hightech/cryptoapp/domain/CryptoFeedLoader.kt
package com.hightech.cryptoapp.domain
========
package domain
>>>>>>>> origin/main:app/src/main/java/domain/CryptoFeedLoader.kt

import kotlinx.coroutines.flow.Flow

sealed class CryptoFeedResult {
    data class Success(val cryptoFeedItems: List<CryptoFeedItem>) : CryptoFeedResult()
    data class Failure(val throwable: Throwable) : CryptoFeedResult()
}

interface CryptoFeedLoader {
    fun load(): Flow<CryptoFeedResult>
}