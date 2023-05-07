package dev.shushant.network.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun getEngine(): HttpClientEngine = Android.create()