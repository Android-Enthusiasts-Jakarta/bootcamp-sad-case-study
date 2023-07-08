package com.hightech.cryptoapp

import kotlinx.coroutines.flow.Flow

sealed class HttpClientResult {
    data class Failure(val throwable: Throwable) : HttpClientResult()
}

interface HttpClient {
    fun get(): Flow<HttpClientResult>
}