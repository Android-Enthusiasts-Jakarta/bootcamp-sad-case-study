package com.hightech.cryptoapp.crypto.feed.ui.navigation

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hightech.cryptoapp.crypto.feed.di.CryptoFeedComponent
import com.hightech.cryptoapp.crypto.feed.domain.CryptoFeedItem
import com.hightech.cryptoapp.crypto.feed.presentation.CryptoFeedViewModel
import com.hightech.cryptoapp.crypto.feed.ui.CryptoFeedRoute
import com.hightech.cryptoapp.main.factories.MainComponent

const val cryptoGraphRoute = "crypto_graph_route"
const val cryptoFeedRoute = "crypto_feed_route"

fun NavGraphBuilder.cryptoGraph(
    mainComponent: MainComponent,
    onCryptoClick: (CryptoFeedItem) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = cryptoGraphRoute,
        startDestination = cryptoFeedRoute
    ) {
        composable(
            route = cryptoFeedRoute
        ) {
            val cryptoFeedViewModel: CryptoFeedViewModel = viewModel {
                mainComponent.cryptoFeedComponent().create().cryptoFeedViewModel()
            }

            Log.d("INSTANCE OF 1", "$cryptoFeedViewModel")

            CryptoFeedRoute(
                viewModel = cryptoFeedViewModel,
                onNavigateToCryptoDetails = onCryptoClick
            )
        }
        nestedGraphs()
    }
}