package com.hightech.cryptoapp.crypto.feed.domain

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