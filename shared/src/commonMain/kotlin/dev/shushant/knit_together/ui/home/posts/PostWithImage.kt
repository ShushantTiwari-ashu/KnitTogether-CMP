package dev.shushant.knit_together.ui.home.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.ui.createpost.MediaType
import dev.shushant.knit_together.ui.createpost.PostData
import dev.shushant.knit_together.utils.ExpandingText
import dev.shushant.knit_together.utils.ImageView
import dev.shushant.utils.dimens.getDimens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun PostWithImage(postData: PostData) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White, Color.Transparent, Color.Transparent, Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        shape = RoundedCornerShape(16.getDimens),
        modifier = Modifier.padding(vertical = 20.getDimens)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.getDimens, vertical = 20.getDimens)
                .fillMaxWidth(),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageView(
                    modifier = Modifier.size(35.getDimens)
                        .border(color = Color.White, width = 1.dp, shape = CircleShape)
                        .clip(CircleShape),
                    url = postData.userImage, alpha = 1f,
                    key = postData.userImage.plus(postData.userId),
                )

                Text(
                    postData.userName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black
                )
            }
            IconButton(onClick = {}) {
                Image(painter = painterResource("three_dots.xml"), contentDescription = null)
            }
        }
        ExpandingText(
            text = postData.bodyText ?: "", modifier = Modifier.padding(horizontal = 20.getDimens)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier.padding(horizontal = 20.getDimens, vertical = 20.getDimens)
                .heightIn(max = 250.getDimens).fillMaxWidth()
        ) {
            with(postData.media.first()) {
                when (mediaUrl) {
                    is MediaType.BitmapType -> {
                        Image(
                            bitmap = mediaUrl.bitmap.toImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.background(
                                color = Color(0xd8b8a94),
                                shape = RoundedCornerShape(10.getDimens)
                            ).fillMaxSize().clip(RoundedCornerShape(10.getDimens)),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    is MediaType.PathType -> {
                        mediaUrl.media.preview?.let {
                            Image(
                                bitmap = it.toImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.background(
                                    color = Color(0xd8b8a94),
                                    shape = RoundedCornerShape(10.getDimens)
                                ).fillMaxSize().clip(RoundedCornerShape(10.getDimens)),
                                contentScale = ContentScale.FillBounds
                            )
                        } ?: kotlin.run {
                            ImageView(
                                modifier = Modifier.background(
                                    color = Color(0xd8b8a94),
                                    shape = RoundedCornerShape(10.getDimens)
                                ).fillMaxSize()
                                    .clip(RoundedCornerShape(10.getDimens)),
                                key = mediaUrl.media.path.plus(
                                    postData.media.first().mediaId
                                ),
                                url = mediaUrl.media.path,
                                alpha = 1.0f
                            )
                        }
                    }
                }
            }
        }

    }
}