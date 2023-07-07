//package com.hightech.cryptoapp
//
//import com.hightech.cryptoapp.domain.CoinInfo
//import com.hightech.cryptoapp.domain.CryptoFeed
//import com.hightech.cryptoapp.domain.Raw
//import com.hightech.cryptoapp.domain.Usd
//import com.hightech.cryptoapp.http.CryptoFeedHttpClient
//import com.hightech.cryptoapp.http.HttpClientResult
//import com.hightech.cryptoapp.http.RemoteCryptoFeedLoader
//import com.hightech.cryptoapp.http.RemoteRootCryptoFeed
//import com.hightech.cryptoapp.http.UiResult
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.runBlocking
//import okhttp3.Response
//import org.junit.Test
//
//import org.junit.Assert.*
//
//class LoadCryptoFeedFromRemoteUseCaseTest {
//    @Test
//    fun testInitDoesNotLoad() {
//        val (_, client) = makeSUT()
//
//        assertTrue(client.loadCount == 0)
//    }
//
//    @Test
//    fun testLoadRequestData() = runBlocking {
//        val (sut, client) = makeSUT()
//
//        sut.load().collect()
//
//        assertEquals(1, client.loadCount)
//    }
//
//    @Test
//    fun testLoadRequestDataTwice() = runBlocking {
//        val (sut, client) = makeSUT()
//
//        sut.load().collect()
//        sut.load().collect()
//
//        assertEquals(2, client.loadCount)
//
//        val items = listOf(
//            CryptoFeed(
//                CoinInfo("123", "123", "123"),
//                Raw(Usd(123.0, 123F))),
//            CryptoFeed(
//                CoinInfo("123", "123", "123"),
//                Raw(Usd(123.0, 123F))
//            )
//        )
//
//        client.complete(200, items)
//    }
//}
//
//private fun makeSUT(): Pair<RemoteCryptoFeedLoader, HTTPClientSpy> {
//    val client = HTTPClientSpy()
//    val sut = RemoteCryptoFeedLoader(client)
//    return Pair(sut, client)
//}
//
//class HTTPClientSpy : CryptoFeedHttpClient {
//    var loadCount = 0
//    private val responses: MutableList<Pair<Int, UiResult<*>>> = mutableListOf()
//
//    override suspend fun get(): Flow<HttpClientResult<RemoteRootCryptoFeed>> = flow {
//        loadCount++
//    }
//
//    fun complete(withStatusCode: Int, items: List<CryptoFeed>) {
//
//    }
//
////    fun complete(withStatusCode: Int, items: List<CryptoFeed>): UiResult.Success<List<CryptoFeed>> {
////        return responses.add(withStatusCode, items)
////    }
//
////    fun <T> complete(throwable: Throwable): UiResult<T> {
////        return UiResult.Failure(throwable)
////    }
//}
