package dev.shushant.knit_together.ui.createpost

import co.touchlab.kermit.Logger
import dev.shushant.permission.Permission
import dev.shushant.permission.PermissionState
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.data.Media
import dev.shushant.permission.picker.LocalMediaController
import dev.shushant.permission.picker.MediaSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent

class CreatePostViewModel(private val mediaController: LocalMediaController) : ViewModel(),
    KoinComponent {

    private val _state = MutableStateFlow(CreatePostState())

    private val currentState: CreatePostState get() = state.value

    /**
     * State to be exposed to the UI layer
     */
    val state: StateFlow<CreatePostState> = _state.asStateFlow()


    fun cameraClicked() {
        viewModelScope.launch {
            try {
                mediaController.permissionsController.providePermission(Permission.CAMERA)
            } catch (e: Exception) {
                Logger.d { e.message ?: "" }
            }
            when (mediaController.permissionsController.getPermissionState(Permission.CAMERA)) {
                PermissionState.NotDetermined -> {

                }

                PermissionState.Granted -> {
                    try {
                        val image = mediaController.pickImage(MediaSource.CAMERA)
                        setState { state ->
                            state.copy(
                                media = state.media.apply {
                                    add(MediaType.BitmapType(image))
                                }, size = state.media.size
                            )
                        }
                    } catch (e: Exception) {
                        Logger.d { e.message ?: "" }
                    }
                }

                PermissionState.Denied -> {

                }

                PermissionState.DeniedAlways -> {
                    mediaController.permissionsController.openAppSettings()
                }
            }
        }
    }

    fun galleryClicked() {
        viewModelScope.launch {
            try {
                mediaController.permissionsController.providePermission(Permission.GALLERY)
            } catch (e: Exception) {
                Logger.d { e.message ?: "" }
            }
            when (mediaController.permissionsController.getPermissionState(Permission.GALLERY)) {
                PermissionState.NotDetermined -> {

                }

                PermissionState.Granted -> {
                    try {
                        val image = mediaController.pickMedia()
                        setState { state ->
                            state.copy(
                                media = state.media.apply {
                                    add(MediaType.PathType(image.toAppMedia()))
                                }, size = state.media.size
                            )
                        }
                    } catch (e: Exception) {
                        Logger.d { e.message ?: "" }
                    }
                }

                PermissionState.Denied -> {

                }

                PermissionState.DeniedAlways -> {
                    mediaController.permissionsController.openAppSettings()
                }
            }
        }
    }

    private fun setState(update: (old: CreatePostState) -> CreatePostState): CreatePostState =
        _state.updateAndGet(update)

    fun updateState(it: String) {
        setState { state -> state.copy(bodyText = it) }
    }

    fun createPost() = PostData(
        media = mutableListOf<PostData.PostMedia>().apply {
            currentState.media.forEach {
                add(
                    PostData.PostMedia(
                        mediaUrl = it,
                        mediaId = it.toString(),
                        mediaType = if (it is MediaType.BitmapType) Type.IMAGE else if (it is MediaType.PathType) {
                            if (it.media.type == dev.shushant.permission.data.MediaType.PHOTO) Type.IMAGE
                            else Type.VIDEO
                        } else Type.IMAGE
                    )
                )
            }
        }, bodyText = currentState.bodyText
    )

    fun hasData() = currentState.bodyText.isNotBlank()
}


data class CreatePostState(
    val bodyText: String = "",
    val media: MutableList<MediaType> = mutableListOf(),
    val size: Int = 0
)

sealed class MediaType {
    data class BitmapType(val bitmap: AppBitmap) : MediaType()
    data class PathType(val media: AppMedia) : MediaType()
}

data class AppMedia(
    val name: String,
    val path: String,
    val preview: AppBitmap?,
    val type: dev.shushant.permission.data.MediaType
)

fun Media.toAppMedia() = AppMedia(
    name = name, path = path, preview = preview, type = type
)