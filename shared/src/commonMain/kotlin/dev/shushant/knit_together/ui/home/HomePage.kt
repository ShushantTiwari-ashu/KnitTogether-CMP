package dev.shushant.knit_together.ui.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.ui.home.posts.PostWithImage
import dev.shushant.knit_together.ui.home.storyview.CreateStoryView
import dev.shushant.knit_together.ui.home.storyview.StoryView
import dev.shushant.network.extensions.generateFullName
import dev.shushant.network.model.getCollection
import dev.shushant.resource.navigation.AppState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.ui.viewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun HomePage(navigator: AppState) {
    val state = rememberLazyListState()
    val viewModel = viewModel(HomeViewModel::class) {
        HomeViewModel()
    }
    val homeState by viewModel.state.collectAsStateWithLifecycle(emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .windowInsetsPadding(WindowInsets(left = 20.dp, right = 20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = state
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            ) {
                storyData.forEachIndexed { index, i ->
                    if (index == 0) {
                        CreateStoryView()
                    } else {
                        StoryView(i)
                    }
                }
            }
        }

        items(homeState) {
            PostWithImage(it)
        }
    }
}

val storyData = mutableListOf<Pair<String, String>>().apply {
    repeat(10) {
        add(Pair(generateFullName(), getCollection))
    }
}.toList()




