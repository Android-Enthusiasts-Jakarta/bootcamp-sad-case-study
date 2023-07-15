package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.crypto.feed.di.CryptoFeedComponent
import dagger.Module

@Module(subcomponents = [CryptoFeedComponent::class])
class MainSubcomponents