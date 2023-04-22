package dev.shushant.permission.data

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException

actual interface ImageGallery {
    actual suspend fun getImages(): List<Image>
}