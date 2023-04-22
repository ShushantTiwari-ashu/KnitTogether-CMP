package dev.shushant.network

import dev.shushant.network.api.KnitTogetherApi
import org.koin.dsl.module

val networkModule = module {
    single { KnitTogetherApi() }
}