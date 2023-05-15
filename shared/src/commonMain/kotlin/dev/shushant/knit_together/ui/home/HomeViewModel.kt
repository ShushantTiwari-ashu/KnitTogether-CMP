package dev.shushant.knit_together.ui.home

import dev.shushant.knit_together.ui.createpost.AppMedia
import dev.shushant.knit_together.ui.createpost.MediaType
import dev.shushant.knit_together.ui.createpost.PostData
import dev.shushant.knit_together.ui.createpost.getCollection
import dev.shushant.knit_together.ui.createpost.postText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow<HomePageState>(HomePageState.Initial)
    val state = _state.asStateFlow()

    private fun initializeData() = mutableListOf<PostData>().apply {
        repeat(10) {
            add(
                PostData(
                    bodyText = postText.random(), media = listOf(
                        PostData.PostMedia(
                            mediaUrl = MediaType.PathType(
                                media = AppMedia(
                                    type = dev.shushant.permission.data.MediaType.PHOTO,
                                    name = "",
                                    path = "https://source.unsplash.com/random/?$getCollection",
                                    preview = null
                                )
                            )
                        )
                    )
                )
            )
        }
    }

    fun createPostData(value: PostData? = null) {
        viewModelScope.launch {
            _state.value = HomePageState.Success(postData = initializeData().apply {
                if (value != null) {
                    add(0, value)
                }
            })
        }
    }
}

sealed class HomePageState {
    object Loading : HomePageState()
    object Initial : HomePageState()
    data class Success(val postData: List<PostData> = emptyList()) : HomePageState()
}