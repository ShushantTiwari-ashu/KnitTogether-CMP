package dev.shushant.knit_together.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
internal fun Loader(modifier: Modifier = Modifier.size(48.dp)) {
    var rotation by remember { mutableStateOf(0f) }

    Canvas(
        modifier = modifier.sizeIn(
            maxWidth = 48.dp,
            minWidth = 24.dp,
            maxHeight = 48.dp,
            minHeight = 24.dp
        ),
        onDraw = {
            val arc = 30f
            val strokeWidth = 4.dp.toPx()

            drawArc(
                color = Color.Gray,
                startAngle = rotation,
                sweepAngle = arc,
                useCenter = false,
                style = Stroke(strokeWidth)
            )

            drawArc(
                color = Color.Blue,
                startAngle = rotation + arc,
                sweepAngle = arc,
                useCenter = false,
                style = Stroke(strokeWidth)
            )

            drawArc(
                color = Color.Red,
                startAngle = rotation + 2 * arc,
                sweepAngle = arc,
                useCenter = false,
                style = Stroke(strokeWidth)
            )
        }
    )

    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis { _ ->
                rotation = (rotation + 5f) % 360f
            }
        }
    }
}