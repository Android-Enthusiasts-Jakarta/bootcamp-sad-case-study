package com.hightech.cryptoapp.crypto.feed.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hightech.cryptoapp.crypto.feed.domain.CryptoFeedItem
import com.hightech.cryptoapp.crypto.feed.presentation.CryptoFeedUiState
import com.hightech.cryptoapp.crypto.feed.presentation.CryptoFeedViewModel
import com.hightech.cryptoapp.crypto.feed.ui.components.CryptoFeedList
import com.hightech.cryptoapp.theme.Purple40

@Composable
fun CryptoFeedRoute(
    viewModel: CryptoFeedViewModel = viewModel(factory = CryptoFeedViewModel.FACTORY),
    onNavigateToCryptoDetails: (CryptoFeedItem) -> Unit
) {
    val cryptoFeedUiState by viewModel.cryptoFeedUiState.collectAsStateWithLifecycle()

    Log.d("loadCryptoFeed", "$cryptoFeedUiState")

    CryptoFeedScreen(
        cryptoFeedUiState = cryptoFeedUiState,
        onRefreshCryptoFeed = viewModel::loadCryptoFeed,
        onNavigateToCryptoDetails = onNavigateToCryptoDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CryptoFeedScreen(
    modifier: Modifier = Modifier,
    cryptoFeedUiState: CryptoFeedUiState,
    onRefreshCryptoFeed: () -> Unit,
    onNavigateToCryptoDetails: (CryptoFeedItem) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = cryptoFeedUiState.isLoading,
        onRefresh = onRefreshCryptoFeed
    )

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Crypto Feed",
                    maxLines = 1,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Purple40
            )
        )
    }, content = {
        val contentModifier = modifier
            .padding(it)
            .fillMaxSize()
            .pullRefresh(pullRefreshState)

        LoadingContent(
            pullRefreshState = pullRefreshState,
            loading = cryptoFeedUiState.isLoading,
            empty = when (cryptoFeedUiState) {
                is CryptoFeedUiState.HasCryptoFeed -> false
                is CryptoFeedUiState.NoCryptoFeed -> cryptoFeedUiState.isLoading
            },
            emptyContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    PullRefresh(
                        loading = cryptoFeedUiState.isLoading,
                        pullRefreshState = pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            },
            content = {
                when (cryptoFeedUiState) {
                    is CryptoFeedUiState.HasCryptoFeed -> {
                        CryptoFeedList(
                            contentModifier = contentModifier,
                            items = cryptoFeedUiState.cryptoFeeds,
                            onNavigateToCryptoDetails = onNavigateToCryptoDetails
                        )
                    }

                    is CryptoFeedUiState.NoCryptoFeed -> {
                        if (cryptoFeedUiState.failed.isEmpty()) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                Text(
                                    "Crypto Feed Empty",
                                )
                            }
                        }
                    }
                }
            })

        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                cryptoFeedUiState.failed,
            )
        }
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadingContent(
    loading: Boolean,
    pullRefreshState: PullRefreshState,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    if (empty) {
        emptyContent()
    } else {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            content()

            PullRefresh(
                loading = loading,
                pullRefreshState = pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefresh(
    loading: Boolean,
    pullRefreshState: PullRefreshState,
    modifier: Modifier
) {
    PullRefreshIndicator(
        refreshing = loading,
        state = pullRefreshState,
        modifier = modifier
    )
}