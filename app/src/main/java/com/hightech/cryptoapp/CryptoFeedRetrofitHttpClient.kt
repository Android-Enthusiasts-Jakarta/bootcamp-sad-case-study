package com.hightech.cryptoapp

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