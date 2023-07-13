package com.hightech.cryptoapp.http.usecases

import com.hightech.cryptoapp.domain.CryptoFeedItemsMapper
import com.hightech.cryptoapp.domain.CryptoFeedLoader
import com.hightech.cryptoapp.domain.CryptoFeedResult
import com.hightech.cryptoapp.http.CryptoFeedHttpClient
import com.hightech.cryptoapp.http.HttpClientResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCryptoFeedLoader constructor(private val cryptoFeedHttpClient: CryptoFeedHttpClient):
    CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        cryptoFeedHttpClient.get().collect { result ->
            if (result is HttpClientResult.Success) {
                val cryptoFeed = result.root.data
                if (cryptoFeed.isNotEmpty()) {
                    emit(CryptoFeedResult.Success(CryptoFeedItemsMapper.map(cryptoFeed)))
                } else {
                    emit(CryptoFeedResult.Success(emptyList()))
                }
            }

            if (result is HttpClientResult.Failure) {
                if (result.throwable is InvalidData) {
                    emit(CryptoFeedResult.Failure(InvalidData()))
                }

                if (result.throwable is Connectivity) {
                    emit(CryptoFeedResult.Failure(Connectivity()))
                }
            }
        }
    }
}

class InvalidData : Throwable()
class Connectivity : Throwable()