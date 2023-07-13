package com.hightech.cryptoapp.http.usecases

import android.util.Log
import com.hightech.cryptoapp.domain.CryptoFeedItemsMapper
import com.hightech.cryptoapp.domain.CryptoFeedLoader
import com.hightech.cryptoapp.domain.CryptoFeedResult
import com.hightech.cryptoapp.http.ConnectivityException
import com.hightech.cryptoapp.http.CryptoFeedHttpClient
import com.hightech.cryptoapp.http.HttpClientResult
import com.hightech.cryptoapp.http.InvalidDataException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCryptoFeedLoader constructor(private val cryptoFeedHttpClient: CryptoFeedHttpClient):
    CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        cryptoFeedHttpClient.get().collect { result ->
            when (result) {
                is HttpClientResult.Success -> {
                    val cryptoFeed = result.root.data
                    if (cryptoFeed.isNotEmpty()) {
                        emit(CryptoFeedResult.Success(CryptoFeedItemsMapper.map(cryptoFeed)))
                    } else {
                        emit(CryptoFeedResult.Success(emptyList()))
                    }
                }

                is HttpClientResult.Failure -> {
                    Log.d("loadCryptoFeed", "Failure")
                    when (result.throwable) {
                        is ConnectivityException -> {
                            emit(CryptoFeedResult.Failure(Connectivity()))
                        }

                        is InvalidDataException -> {
                            Log.d("loadCryptoFeed", "InvalidData")
                            emit(CryptoFeedResult.Failure(InvalidData()))
                        }
                    }
                }
            }
        }
    }
}

class InvalidData : Throwable()
class Connectivity : Throwable()