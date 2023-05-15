package dev.shushant.utils.common

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import dev.shushant.network.api.KnitTogetherApi
import dev.shushant.network.expectations.DispatcherDefault
import dev.shushant.network.functional.Either
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * The single source of truth for images
 */
object ImageRepository : KoinComponent {
    private val client: KnitTogetherApi by inject()

    /**
     * Cache of images loaded in the current session.
     */
    private val inMemoryCache = mutableMapOf<String, ByteArray>()

    /**
     * Loads [ImageBitmap] from the specified [url].
     *
     * If image was already loaded with this [url] then it picks it from [inMemoryCache] otherwise
     * loads it from the network and stores it in the cache.
     */
    suspend fun getImageBitmapByUrl(url: String, key: String): ImageBitmap {
        return withContext(DispatcherDefault) {
            val bytes = inMemoryCache.getOrPut(key) {
                when (val result = client.getImageBitmapByUrl(url)) {
                    is Either.Failure -> throw Exception(result.a.message)
                    is Either.Success -> result.b
                }
            }
            val bitmap = withContext(DispatcherDefault) {
                Image.makeFromEncoded(bytes).toComposeImageBitmap()
            }
            bitmap
        }
    }
}