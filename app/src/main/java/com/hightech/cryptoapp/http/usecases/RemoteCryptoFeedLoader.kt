package com.hightech.cryptoapp.http.usecases

import com.hightech.cryptoapp.domain.CryptoFeedItemsMapper
import com.hightech.cryptoapp.domain.CryptoFeedResult
import com.hightech.cryptoapp.http.HttpClient
import com.hightech.cryptoapp.http.HttpClientResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCryptoFeedLoader constructor(private val httpClient: HttpClient) {
    fun load(): Flow<CryptoFeedResult> = flow {
        httpClient.get().collect { result ->
            if (result is HttpClientResult.Success) {
                val cryptoFeed = result.root.data
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