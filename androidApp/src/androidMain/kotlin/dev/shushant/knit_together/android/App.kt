package dev.shushant.knit_together.android

import android.app.Application
import dev.shushant.knit_together.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@App)
        }
    }
}