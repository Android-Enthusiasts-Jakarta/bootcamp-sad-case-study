package com.hightech.cryptoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hightech.cryptoapp.domain.CryptoFeedUiState
import com.hightech.cryptoapp.domain.CryptoFeedViewModel
import com.hightech.cryptoapp.theme.CryptoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CryptoFeedRoute()
                }
            }
        }
    }
}

@Composable
fun CryptoFeedRoute(
    viewModel: CryptoFeedViewModel = viewModel(factory = CryptoFeedViewModel.FACTORY)
) {
    val cryptoFeedUiState by viewModel.cryptoFeedUiState.collectAsStateWithLifecycle()

    CryptoFeedScreen(
        cryptoFeedUiState = cryptoFeedUiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoFeedScreen(
    modifier: Modifier = Modifier,
    cryptoFeedUiState: CryptoFeedUiState,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text("Crypto Feed", maxLines = 1)
        })
    }, content = {
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