package com.hightech.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hightech.cryptoapp.domain.CryptoFeedItem
import com.hightech.cryptoapp.domain.CryptoFeedLoader
import com.hightech.cryptoapp.domain.CryptoFeedResult
import com.hightech.cryptoapp.http.usecases.Connectivity
import com.hightech.cryptoapp.http.usecases.InvalidData
import com.hightech.cryptoapp.main.factories.RemoteCryptoFeedLoaderFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface CryptoFeedUiState {
    val isLoading: Boolean
    val failed: String

    data class NoCryptoFeed(
        override val isLoading: Boolean,
        override val failed: String,
    ) : CryptoFeedUiState
}

data class CryptoFeedViewModelState(
    val isLoading: Boolean = false,
    val cryptoFeeds: List<CryptoFeedItem>? = null,
    val failed: String = ""
) {
    fun toCryptoFeedUiState(): CryptoFeedUiState =
        CryptoFeedUiState.NoCryptoFeed(
            isLoading = isLoading,
            failed = failed
        )
}

class CryptoFeedViewModel constructor(
    private val cryptoFeedLoader: CryptoFeedLoader
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        CryptoFeedViewModelState(
            isLoading = true,
            failed = ""
        )
    )

    val cryptoFeedUiState = viewModelState
        .map(CryptoFeedViewModelState::toCryptoFeedUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toCryptoFeedUiState()
        )

    init {
        loadCryptoFeed()
    }

    private fun loadCryptoFeed() {
        viewModelScope.launch {
            cryptoFeedLoader.load().collect { result ->
                if (result is CryptoFeedResult.Failure) {
                    viewModelState.update {
                        it.copy(
                            failed = when (result.throwable) {
                                is Connectivity -> {
                                    "Connectivity"
                                }

                                is InvalidData -> {
                                    "Invalid Data"
                                }

                                else -> {
                                    "Something Went Wrong"
                                }
                            },
                            isLoading = false
                        )
                    }
                }
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