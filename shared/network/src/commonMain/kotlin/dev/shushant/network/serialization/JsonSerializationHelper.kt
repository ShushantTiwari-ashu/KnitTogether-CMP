package dev.shushant.network.serialization

import kotlinx.serialization.json.Json

class JsonSerializationHelper {
    companion object {
        fun JsonX(): Json {
            return Json {
                ignoreUnknownKeys = true
                isLenient = true
                allowSpecialFloatingPointValues = true
                encodeDefaults = true
            }
        }
    }
}