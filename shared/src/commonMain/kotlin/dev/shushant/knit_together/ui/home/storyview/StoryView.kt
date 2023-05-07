package dev.shushant.knit_together.ui.home.storyview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.utils.ImageView
import dev.shushant.resource.dimens.getDimens
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun StoryView(i: Pair<String, String>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.widthIn(max = 100.getDimens)
    ) {
        Box {
            ImageView(
                modifier = Modifier.fillMaxSize().background(
                    color = Color(0xd8b8a94),
                    shape = RoundedCornerShape(10.getDimens)
                ).size(
                    100.getDimens
                ).clip(RoundedCornerShape(10.getDimens)).border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xb37d72e0),
                            Color(0xb3c48de4),
                        )
                    ),
                    shape = RoundedCornerShape(10.getDimens),
                ),
                alpha = 0.6f,
                url = "https://source.unsplash.com/random/?${i.second}",
                key = i.second,
            )
            ImageView(
                modifier = Modifier.align(Alignment.Center).size(35.getDimens)
                    .border(color = Color.White, width = 1.dp, shape = CircleShape)
                    .clip(CircleShape),
                url = "https://i.pravatar.cc/300?u=${i.first}",
                key = i.first,
                alpha = 1f
            )
        }

        Text(
            i.first,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}