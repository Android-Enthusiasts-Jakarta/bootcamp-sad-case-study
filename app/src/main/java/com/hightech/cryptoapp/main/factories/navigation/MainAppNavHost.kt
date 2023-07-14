package com.hightech.cryptoapp.main.factories.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hightech.cryptoapp.crypto.details.navigation.cryptoDetailScreen
import com.hightech.cryptoapp.crypto.details.navigation.navigateToCryptoDetails
import com.hightech.cryptoapp.crypto.feed.ui.navigation.cryptoGraph
import com.hightech.cryptoapp.crypto.feed.ui.navigation.cryptoGraphRoute

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = cryptoGraphRoute
) {
    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        cryptoGraph(
            onCryptoClick = navHostController::navigateToCryptoDetails
        ) {
            cryptoDetailScreen(
                popBackStack = navHostController::popBackStack
            )
        }
    }
}