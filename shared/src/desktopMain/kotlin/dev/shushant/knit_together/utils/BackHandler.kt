package dev.shushant.knit_together.utils

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import java.awt.Window
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    val offsetX = remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    if (change.positionChange() != Offset.Zero) change.consume()
                    val newOffsetX = offsetX.value + dragAmount.x
                    if (newOffsetX >= 0 && newOffsetX <= size.width - 100.dp.toPx()) {
                        offsetX.value = newOffsetX
                    }
                }
            }
    ) {
    }
}