package com.hightech.cryptoapp.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hightech.cryptoapp.main.factories.RemoteCryptoFeedLoaderFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

sealed class CryptoFeedResult {
    data class Success(val cryptoFeedItems: List<CryptoFeedItem>?) : CryptoFeedResult()
    data class Failure(val throwable: Throwable) : CryptoFeedResult()
}

interface CryptoFeedLoader {
    fun load(): Flow<CryptoFeedResult>
}

class CryptoFeedViewModel constructor(
    private val cryptoFeedLoader: CryptoFeedLoader
) : ViewModel() {

    init {
        loadCryptoFeed()
    }

    private fun loadCryptoFeed() {
        viewModelScope.launch {
            cryptoFeedLoader.load().collect { result ->
                Log.d("CryptoFeedViewModel", "loadCryptoFeed execute $result")
            }
        }
    }

    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CryptoFeedViewModel(
                    RemoteCryptoFeedLoaderFactory.createRemoteCryptoFeedLoader()
                )
            }
        }
    }
}