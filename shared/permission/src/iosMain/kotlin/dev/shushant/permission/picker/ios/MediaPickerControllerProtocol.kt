package dev.shushant.permission.picker.ios

import dev.shushant.permission.PermissionsController
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.data.FileMedia
import dev.shushant.permission.data.Media
import dev.shushant.permission.picker.MediaSource
import platform.UIKit.UIViewController

interface MediaPickerControllerProtocol {
    val permissionsController: PermissionsController

    fun bind(viewController: UIViewController)

    suspend fun pickImage(source: MediaSource): AppBitmap
    suspend fun pickImage(source: MediaSource, maxWidth: Int, maxHeight: Int): AppBitmap
    suspend fun pickMedia(): Media
    suspend fun pickFiles(): FileMedia
}