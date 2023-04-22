package dev.shushant.network.di

import dev.shushant.network.expectations.Application
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object IOS : KoinComponent {
    val application: Application by inject()
}