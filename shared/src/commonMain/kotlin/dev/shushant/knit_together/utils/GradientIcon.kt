package dev.shushant.knit_together.utils

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun GradientIcon(onClick: () -> Unit, icon: ImageVector, modifier: Modifier) {
    IconButton(onClick, modifier = modifier) {
        Icon(
            modifier = Modifier
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(brushGradient, blendMode = BlendMode.SrcAtop)
                    }
                },
            imageVector = icon,
            contentDescription = null,
        )
    }
}

val brushGradient = Brush.linearGradient(
    listOf(
        Color(0xb37d72e0),
        Color(0xb3c48de4),
    ),
)