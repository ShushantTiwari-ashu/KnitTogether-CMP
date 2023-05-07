package dev.shushant.knit_together.utils

import androidx.compose.runtime.staticCompositionLocalOf
import dev.shushant.permission.picker.LocalMediaController


internal val LocalLocalMediaController = staticCompositionLocalOf<LocalMediaController> {
    error("Unable to get the controller")
}