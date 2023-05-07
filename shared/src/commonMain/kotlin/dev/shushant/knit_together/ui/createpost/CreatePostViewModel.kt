package dev.shushant.knit_together.ui.createpost

import dev.shushant.network.model.PostData
import dev.shushant.permission.Permission
import dev.shushant.permission.PermissionState
import dev.shushant.permission.picker.LocalMediaController
import dev.shushant.permission.picker.MediaSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class CreatePostViewModel(private val mediaController: LocalMediaController) : ViewModel() {

    private val _state = MutableStateFlow(CreatePostState())

    private val currentState: CreatePostState get() = state.value

    /**
     * State to be exposed to the UI layer
     */
    val state: StateFlow<CreatePostState> = _state.asStateFlow()



    fun cameraClicked() {
        viewModelScope.launch {
            mediaController.permissionsController.providePermission(Permission.CAMERA)
            when (mediaController.permissionsController.getPermissionState(Permission.CAMERA)) {
                PermissionState.NotDetermined -> {

                }

                PermissionState.Granted -> {
                    val image = mediaController.pickImage(MediaSource.CAMERA)
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
            mediaController.permissionsController.providePermission(Permission.GALLERY)
        }
    }

    private fun setState(update: (old: CreatePostState) -> CreatePostState): CreatePostState =
        _state.updateAndGet(update)
}


data class CreatePostState(
    val postData: PostData = PostData()
)