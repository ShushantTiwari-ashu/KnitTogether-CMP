package dev.shushant.resource.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun AsyncImage(
    imageUrl: String?,
    loadingPlaceholder: @Composable BoxScope.() -> Unit,
    errorPlaceholder: @Composable BoxScope.() -> Unit,
    contentDescription: String?,
    modifier: Modifier,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    colorFilter: ColorFilter?,
    filterQuality: FilterQuality
) {
    val imageState by rememberImageState(imageUrl)

    Box(modifier = modifier) {
        when (val state = imageState) {
            ImageState.Error -> {
                errorPlaceholder()
            }

            ImageState.Loading -> {
                loadingPlaceholder()
            }

            is ImageState.Success -> {
                Image(
                    bitmap = state.imageBitmap,
                    contentDescription = contentDescription,
                    modifier = modifier,
                    alignment = alignment,
                    contentScale = contentScale,
                    alpha = alpha,
                    colorFilter = colorFilter,
                    filterQuality = filterQuality,
                )
            }
        }
    }
}

/**
 * Remembers the state of an image in the composition with [url]
 */
@Composable
private fun rememberImageState(url: String?): State<ImageState> {
    val initialState = if (url == null) ImageState.Error else ImageState.Loading
    return produceState(initialState, key1 = url) {
        if (url != null) {
            value = ImageState.Loading
            runCatching {
                value = ImageState.Success(ImageRepository.getImageBitmapByUrl(url))
            }.getOrElse {
                value = ImageState.Error
            }
        }
    }
}