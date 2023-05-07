package dev.shushant.permission.picker

import android.app.Activity
import android.content.Intent
import dev.shushant.permission.PermissionsController
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.data.FileMedia
import dev.shushant.permission.data.Media

actual interface LocalMediaController {
    actual val permissionsController: PermissionsController

    actual suspend fun pickImage(source: MediaSource): AppBitmap
    actual suspend fun pickImage(source: MediaSource, maxWidth: Int, maxHeight: Int): AppBitmap
    actual suspend fun pickMedia(): Media
    actual suspend fun pickFiles(): FileMedia
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    companion object {
        operator fun invoke(
            context: Activity
        ): LocalMediaController {
            return LocalMediaControllerImpl(
                permissionsController = PermissionsController(context),
                context = context
            )
        }
    }
}