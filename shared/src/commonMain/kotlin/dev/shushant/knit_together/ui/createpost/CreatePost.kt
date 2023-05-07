package dev.shushant.knit_together.ui.createpost

import androidx.compose.runtime.Composable
import co.touchlab.kermit.Logger
import dev.shushant.knit_together.utils.LocalLocalMediaController
import dev.shushant.resource.navigation.AppState
import moe.tlaster.precompose.ui.viewModel

@Composable
fun CreatePost(appState: AppState) {
    val mediaPickerController = LocalLocalMediaController.current
    val viewModel = viewModel(CreatePostViewModel::class, listOf(mediaPickerController)) {
        CreatePostViewModel(mediaPickerController)
    }

    appState.onPostClick = {
        Logger.d { "onPostClick" }
    }

}
