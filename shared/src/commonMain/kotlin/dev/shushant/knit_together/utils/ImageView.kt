package dev.shushant.knit_together.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.shushant.utils.common.AsyncImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ImageView(
    modifier: Modifier,
    url: String = "https://source.unsplash.com/random/?HillStation",
    alpha: Float = 0.8f,
    key: String = Random.nextInt(0, 10).toString()
) {
    dev.shushant.utils.common.AsyncImage(
        imageUrl = url,
        contentScale = ContentScale.FillBounds,
        alpha = alpha,
        key = key,
        modifier = modifier,
        filterQuality = FilterQuality.High,
        contentDescription = null,
        loadingPlaceholder = {
            Loader(
                modifier = Modifier.align(
                    Alignment.Center
                ).size(24.dp).fillMaxSize()
            )
        },
        errorPlaceholder = {
            Icon(
                painter = painterResource("splash.png"),
                contentDescription = null,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }

    )
}