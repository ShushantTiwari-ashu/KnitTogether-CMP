package dev.shushant.permission.data

class ImageGalleryImpl : ImageGallery {
    override suspend fun getImages(): List<Image> {
        return emptyList()
    }
}