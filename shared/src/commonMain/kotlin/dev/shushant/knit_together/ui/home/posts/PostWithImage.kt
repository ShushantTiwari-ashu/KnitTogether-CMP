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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.utils.ImageView
import dev.shushant.knit_together.utils.PostBodyText
import dev.shushant.network.extensions.splitText
import dev.shushant.network.model.PostData
import dev.shushant.resource.dimens.getDimens
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
                    key = postData.media.mediaUrl.plus(postData.userId),
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
        val str = buildAnnotatedString {
            val pair =
                splitText(postData.bodyText)
            pair.forEach {
                if (it.second) {
                    withStyle(SpanStyle(color = Color(0XFF7268DC))) {
                        append(it.first)
                    }
                } else {
                    append(it.first)
                }
            }
        }
        PostBodyText(
            annotatedDescription = str, modifier = Modifier.padding(horizontal = 20.getDimens)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier.padding(horizontal = 20.getDimens, vertical = 20.getDimens)
                .heightIn(max = 250.getDimens).fillMaxWidth()
        ) {
            ImageView(
                modifier = Modifier.background(
                    color = Color(0xd8b8a94), shape = RoundedCornerShape(10.getDimens)
                ).fillMaxSize()
                    .clip(RoundedCornerShape(10.getDimens)),
                key = postData.media.mediaUrl.plus(postData.media.mediaId),
                url = postData.media.mediaUrl,
                alpha = 1.0f
            )
        }

    }
}