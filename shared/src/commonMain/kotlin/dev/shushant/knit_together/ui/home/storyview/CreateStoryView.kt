package dev.shushant.knit_together.ui.home.storyview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.shushant.resource.dimens.getDimens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun CreateStoryView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.widthIn(max = 100.getDimens)
    ) {
        Box(
            modifier = Modifier.background(
                color = Color(0xd8b8a94),
                shape = RoundedCornerShape(10.getDimens)
            ).size(
                100.getDimens
            ).clip(RoundedCornerShape(10.getDimens))
        ) {
            Image(
                painterResource("plus_gray.xml"),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text("Your Story", style = MaterialTheme.typography.labelMedium, color = Color.Black)
    }
}