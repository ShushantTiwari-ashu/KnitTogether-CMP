package dev.shushant.knit_together.ui.home

import dev.shushant.network.model.PostData
import dev.shushant.network.model.getCollection
import dev.shushant.network.model.postText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow<List<PostData>>(emptyList())
    val state = _state.asStateFlow()

    init {
        initializeData()
    }

    private fun initializeData() {
        viewModelScope.launch {
            _state.value = mutableListOf<PostData>().apply {
                repeat(10) {
                    add(
                        PostData(
                            bodyText = postText.random(),
                            media = PostData.PostMedia(mediaUrl = "https://source.unsplash.com/random/?$getCollection")
                        )
                    )
                }
            }
        }
    }
}