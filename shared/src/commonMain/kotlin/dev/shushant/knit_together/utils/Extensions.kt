package dev.shushant.knit_together.utils

import androidx.compose.runtime.staticCompositionLocalOf
import dev.shushant.permission.picker.MediaPickerController


internal val LocalMediaPickerController = staticCompositionLocalOf<MediaPickerController> {
    error("Unable to get the controller")
}