<<<<<<<< HEAD:app/src/main/java/com/hightech/cryptoapp/domain/CryptoFeed.kt
package com.hightech.cryptoapp.domain
========
package domain
>>>>>>>> origin/main:app/src/main/java/domain/CryptoFeed.kt

data class CryptoFeedItem(
    val coinInfo: CoinInfoItem,
    val raw: RawItem,
)

data class CoinInfoItem(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String
)

data class RawItem(
    val usd: UsdItem
)

data class UsdItem(
    val price: Double,
    val changePctDay: Float
)