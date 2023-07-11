package com.hightech.cryptoapp.http.usecases

import com.hightech.cryptoapp.domain.CoinInfoItem
import com.hightech.cryptoapp.domain.CryptoFeedItem
import com.hightech.cryptoapp.domain.CryptoFeedItemsMapper
import com.hightech.cryptoapp.domain.CryptoFeedResult
import com.hightech.cryptoapp.domain.RawItem
import com.hightech.cryptoapp.domain.UsdItem
import com.hightech.cryptoapp.http.HttpClient
import com.hightech.cryptoapp.http.HttpClientResult
import com.hightech.cryptoapp.http.RemoteCryptoFeedItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoFeedItemsMapper {
    companion object {
        fun map(items: List<RemoteCryptoFeedItem>): List<CryptoFeedItem> {
            return items.map {
                CryptoFeedItem(
                    coinInfo = CoinInfoItem(
                        it.remoteCoinInfo.id.orEmpty(),
                        it.remoteCoinInfo.name.orEmpty(),
                        it.remoteCoinInfo.fullName.orEmpty(),
                        it.remoteCoinInfo.imageUrl.orEmpty()
                    ),
                    raw = RawItem(
                        usd = UsdItem(
                            it.remoteRaw.usd.price ?: 0.0,
                            it.remoteRaw.usd.changePctDay ?: 0F
                        )
                    )
                )
            }
        }
    }
}

class RemoteCryptoFeedLoader constructor(private val httpClient: HttpClient) {
    fun load(): Flow<CryptoFeedResult> = flow {
        httpClient.get().collect { result ->
            if (result is HttpClientResult.Success) {
                val cryptoFeed = result.data
                if (!cryptoFeed.isNullOrEmpty()) {
                    emit(CryptoFeedResult.Success(CryptoFeedItemsMapper.map(cryptoFeed)))
                } else {
                    emit(CryptoFeedResult.Success(emptyList()))
                }
            }

            if (result is HttpClientResult.Failure) {
                emit(CryptoFeedResult.Failure(InvalidData()))
            }

            if (result is HttpClientResult.Failure) {
                emit(CryptoFeedResult.Failure(Connectivity()))
            }
        }
    }
}

class InvalidData : Throwable()
class Connectivity : Throwable()