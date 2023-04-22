package dev.shushant.permission.picker

import dev.shushant.permission.PermissionsController
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.data.FileMedia
import dev.shushant.permission.data.Media

internal const val DEFAULT_MAX_IMAGE_WIDTH = 1024
internal const val DEFAULT_MAX_IMAGE_HEIGHT = 1024
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect interface MediaPickerController {
    val permissionsController: PermissionsController

    suspend fun pickImage(source: MediaSource): AppBitmap
    suspend fun pickImage(source: MediaSource, maxWidth: Int, maxHeight: Int): AppBitmap
    suspend fun pickMedia(): Media
    suspend fun pickFiles(): FileMedia
}