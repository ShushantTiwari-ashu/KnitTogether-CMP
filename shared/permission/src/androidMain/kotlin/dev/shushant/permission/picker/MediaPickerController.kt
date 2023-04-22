package dev.shushant.permission.picker

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import dev.shushant.permission.PermissionsController
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.data.FileMedia
import dev.shushant.permission.data.Media

actual interface MediaPickerController {
    actual val permissionsController: PermissionsController

    actual suspend fun pickImage(source: MediaSource): AppBitmap
    actual suspend fun pickImage(source: MediaSource, maxWidth: Int, maxHeight: Int): AppBitmap
    actual suspend fun pickMedia(): Media
    actual suspend fun pickFiles(): FileMedia
    fun bind(lifecycle: Lifecycle, fragmentManager: FragmentManager)

    companion object {
        operator fun invoke(
            permissionsController: PermissionsController,
            pickerFragmentTag: String = "MediaControllerPicker",
            filePickerFragmentTag: String = "FileMediaControllerPicker"
        ): MediaPickerController {
            return MediaPickerControllerImpl(
                permissionsController = permissionsController,
                pickerFragmentTag = pickerFragmentTag
            )
        }
    }
}