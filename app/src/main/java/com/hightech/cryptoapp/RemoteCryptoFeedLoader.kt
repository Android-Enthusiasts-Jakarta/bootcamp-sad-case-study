package com.hightech.cryptoapp

import com.hightech.cryptoapp.domain.CryptoFeedResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

sealed class HttpClientResult {
    data class Failure(val throwable: Throwable) : HttpClientResult()
}

class CryptoFeedRetrofitHttpClient {
     fun get(): Flow<HttpClientResult> = flow {
        try { } catch (throwable: Throwable) {
            if (throwable is IOException) {
                emit(HttpClientResult.Failure(Connectivity()))
            }
        }
    }.flowOn(Dispatchers.IO)
}

class RemoteCryptoFeedLoader constructor(private val httpClient: CryptoFeedRetrofitHttpClient) {
    fun load(): Flow<CryptoFeedResult> = flow {
        httpClient.get().collect { result ->
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