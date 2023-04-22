package dev.shushant.knit_together.di

import dev.shushant.network.networkModule
import dev.shushant.persistence.AppSettings
import dev.shushant.persistence.platformModule
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

