package dev.shushant.network.extensions

import dev.shushant.network.serialization.JsonSerializationHelper.Companion.JsonX
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString


fun <T> T.toJsonString(serializer: SerializationStrategy<T>): String {
    return JsonX().encodeToString(serializer, this)
}

fun <T> String.toObject(
    deserializer: DeserializationStrategy<T>
): T {
    return JsonX().decodeFromString(
        deserializer,
        this
    )
}

inline fun <reified T> String.toObject(): T {
    return JsonX().decodeFromString<T>(this)
}