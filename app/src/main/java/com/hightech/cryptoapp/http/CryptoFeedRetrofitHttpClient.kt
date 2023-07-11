package com.hightech.cryptoapp.http

import com.hightech.cryptoapp.http.usecases.Connectivity
import com.hightech.cryptoapp.http.usecases.InvalidData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class CryptoFeedRetrofitHttpClient constructor(
    private val cryptoFeedService: CryptoFeedService
): CryptoFeedHttpClient {
    override fun get(): Flow<HttpClientResult> = flow {
        try {
            emit(HttpClientResult.Success(cryptoFeedService.get()))
        } catch (throwable: Throwable) {
            if (throwable is IOException) {
                emit(HttpClientResult.Failure(Connectivity()))
            }

            if (throwable is HttpException) {
                if (throwable.code() == 422) {
                    HttpClientResult.Failure(InvalidData())
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}