package com.hightech.cryptoapp.crypto.feed.http

import android.util.Log
import com.hightech.cryptoapp.main.factories.DispatcherIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoFeedRetrofitHttpClient @Inject constructor(
    private val cryptoFeedService: CryptoFeedService,
    @DispatcherIO private val dispatcher: CoroutineDispatcher
): CryptoFeedHttpClient {
    override fun get(): Flow<HttpClientResult> = flow {
        try {
            Log.d("Thread nih gan", Thread.currentThread().name)
            emit(HttpClientResult.Success(cryptoFeedService.get()))
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> {
                    emit(HttpClientResult.Failure(ConnectivityException()))
                }
                is HttpException -> {
                    if (throwable.code() == 422) {
                        emit(HttpClientResult.Failure(InvalidDataException()))
                    }
                }
                else -> {
                    emit(HttpClientResult.Failure(InvalidDataException()))
                }
            }
        }
    }.flowOn(dispatcher)
}