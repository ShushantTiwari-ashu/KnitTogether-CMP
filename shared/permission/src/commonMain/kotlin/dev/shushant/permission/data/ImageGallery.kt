package dev.shushant.permission.data

expect interface ImageGallery {
    suspend fun getImages(): List<Image>
}