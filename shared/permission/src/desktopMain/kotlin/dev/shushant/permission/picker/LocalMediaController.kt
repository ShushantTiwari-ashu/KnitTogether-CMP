package dev.shushant.permission.picker

import dev.shushant.permission.PermissionsController
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.data.FileMedia
import dev.shushant.permission.data.Media


actual interface LocalMediaController {
    actual val permissionsController: PermissionsController
    actual suspend fun pickImage(source: MediaSource): AppBitmap
    actual suspend fun pickImage(
        source: MediaSource,
        maxWidth: Int,
        maxHeight: Int
    ): AppBitmap

    actual suspend fun pickMedia(): Media
    actual suspend fun pickFiles(): FileMedia

    companion object {
        fun invoke(): LocalMediaController = LocalMediaControllerImpl()
    }

}

class LocalMediaControllerImpl : LocalMediaController {
    override val permissionsController: PermissionsController
        get() = PermissionsController.invoke()

    override suspend fun pickImage(source: MediaSource): AppBitmap {
        TODO("Not yet implemented")
    }

    override suspend fun pickImage(source: MediaSource, maxWidth: Int, maxHeight: Int): AppBitmap {
        TODO("Not yet implemented")
    }

    override suspend fun pickMedia(): Media {
        TODO("Not yet implemented")
    }

    override suspend fun pickFiles(): FileMedia {
        TODO("Not yet implemented")
    }

}
