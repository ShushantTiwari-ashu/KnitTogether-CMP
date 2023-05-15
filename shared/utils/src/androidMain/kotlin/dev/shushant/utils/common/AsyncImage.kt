package dev.shushant.utils.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size.Companion.ORIGINAL
import dev.shushant.utils.dimens.getDimens

@Composable
actual fun AsyncImage(
    imageUrl: String?,
    key: String,
    loadingPlaceholder: @Composable BoxScope.() -> Unit,
    errorPlaceholder: @Composable BoxScope.() -> Unit,
    contentDescription: String?,
    modifier: Modifier,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    colorFilter: ColorFilter?,
    filterQuality: FilterQuality,
) {
    val model = ImageRequest.Builder(LocalContext.current).data(imageUrl).diskCacheKey(key)
        .memoryCacheKey(key).allowConversionToBitmap(true).size(ORIGINAL).scale(Scale.FIT).build()
    val painter = rememberAsyncImagePainter(model)

    Box(modifier = modifier.clip(RoundedCornerShape(10.getDimens))) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                loadingPlaceholder()
            }

            is AsyncImagePainter.State.Error -> {
                errorPlaceholder()
            }

            is AsyncImagePainter.State.Success -> {
                Image(
                    bitmap = (painter.state as AsyncImagePainter.State.Success).result.drawable.toBitmap()
                        .asImageBitmap(),
                    contentDescription = contentDescription,
                    colorFilter = colorFilter,
                    alpha = alpha,
                    alignment = alignment,
                    contentScale = ContentScale.FillBounds
                )
            }

            else -> {}
        }

    }
}