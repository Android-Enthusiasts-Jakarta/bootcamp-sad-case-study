package com.hightech.cryptoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
    CryptoFeedScreen()
}

@Composable
fun CryptoFeedScreen() {
    Text(text = "Starter Project")
}