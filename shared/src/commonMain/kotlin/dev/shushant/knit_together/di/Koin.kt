package dev.shushant.knit_together.di

import dev.shushant.network.HTTPHelper
import dev.shushant.network.api.KnitTogetherApi
import dev.shushant.network.api.getEngine
import dev.shushant.network.serialization.JsonSerializationHelper
import dev.shushant.persistence.AppSettings
import dev.shushant.persistence.platformModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), persistenceModule, networkModule)
    }

fun initKoin() = initKoin {}

val persistenceModule = module {
    single { AppSettings(get()) }
}

val networkModule = module {
    single { createJson() }
    single { createHttpClient(get()) }
    single { HTTPHelper(client = get()) }
    single { KnitTogetherApi(get()) }
}

fun createJson() = JsonSerializationHelper.JsonX()

fun createHttpClient(json: Json) = HttpClient(getEngine()) {
    expectSuccess = true
    install(HttpTimeout) {
        val timeout = 30000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
    install(ContentNegotiation) {
        json(json)
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(ResponseObserver) {
        this.onResponse {
            co.touchlab.kermit.Logger.e { it.toString() }
        }
    }
}

