package com.hightech.cryptoapp.http

import kotlinx.coroutines.flow.Flow

sealed class HttpClientResult {
    data class Success(val data: List<RemoteCryptoFeedItem>?) : HttpClientResult()
    data class Failure(val throwable: Throwable) : HttpClientResult()
}

interface HttpClient {
    fun get(): Flow<HttpClientResult>
}