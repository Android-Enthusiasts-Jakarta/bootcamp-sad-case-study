package com.hightech.cryptoapp.crypto.feed.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hightech.cryptoapp.crypto.feed.domain.CryptoFeedItem

@Composable
fun CryptoFeedList(
    modifier: Modifier = Modifier,
    contentModifier: Modifier,
    items: List<CryptoFeedItem>,
    onNavigateToCryptoDetails: (CryptoFeedItem) -> Unit
) {
    Column(
        modifier = contentModifier
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(items) { cryptoFeed ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToCryptoDetails(cryptoFeed) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(20.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = modifier.weight(2f)
                        ) {
                            Box(
                                modifier = modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFFCFCFF))
                                    .size(50.dp)
                            ) {
                                AsyncImage(
                                    model = "https://cryptocompare.com/${cryptoFeed.coinInfo.imageUrl}",
                                    contentDescription = "Icon",
                                    modifier = modifier
                                        .size(50.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = modifier.weight(5f)
                        ) {
                            Text(
                                text = cryptoFeed.coinInfo.fullName,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Start
                            )

                            Text(
                                text = cryptoFeed.coinInfo.name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                                textAlign = TextAlign.Start
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = modifier.weight(3f)
                        ) {
                            Text(
                                text = "$" + cryptoFeed.raw.usd.price,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Text(
                                text = cryptoFeed.raw.usd.changePctDay.toString() + "%",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (cryptoFeed.raw.usd.changePctDay < 0) Color.Red else Color.Green
                            )
                        }
                    }
                }
                Divider(color = Color.Gray)
            }
        }
    }
}