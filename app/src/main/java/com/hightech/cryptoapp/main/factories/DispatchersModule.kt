package com.hightech.cryptoapp.main.factories

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object DispatchersModule {

    @DispatcherIO
    @Singleton
    @Provides
    fun provideDispatchersIO(): CoroutineDispatcher = Dispatchers.IO
}