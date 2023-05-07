package dev.shushant.network.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

actual fun getEngine(): HttpClientEngine = CIO.create()