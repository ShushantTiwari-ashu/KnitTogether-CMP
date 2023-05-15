package dev.shushant.knit_together.ui.createpost

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.utils.LocalLocalMediaController
import dev.shushant.knit_together.utils.getSpannedString
import dev.shushant.utils.common.AsyncImage
import dev.shushant.utils.dimens.getDimens
import dev.shushant.utils.navigation.AppState
import dev.shushant.utils.navigation.TopLevelDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.ui.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun CreatePost(appState: AppState, createPost: (PostData?) -> Unit) {
    val mediaPickerController = LocalLocalMediaController.current
    val viewModel = viewModel(CreatePostViewModel::class, listOf(mediaPickerController)) {
        CreatePostViewModel(mediaPickerController)
    }

    CreatePostContent(viewModel)

    appState.onPostClick = {
        if (viewModel.hasData()) {
            createPost.invoke(viewModel.createPost())
            appState.navigator.popBackStack()
            appState.navigate(TopLevelDestination.HOME.name, true)
        }
    }

}

@OptIn(
    ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class,
    ExperimentalResourceApi::class
)
@Composable
fun CreatePostContent(viewModel: CreatePostViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle(null)
    val text = state?.bodyText?.let { getSpannedString(it) } ?: AnnotatedString("")
    val localFocusManager = LocalFocusManager.current
    val media = state?.media
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(annotatedString = text)) }
    val textFieldValue = textFieldValueState.copy(annotatedString = text)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection || textFieldValue.composition != textFieldValueState.composition) {
            textFieldValueState = textFieldValue
        }
    }
    var lastTextValue by remember(text) { mutableStateOf(text) }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.getDimens)
            .verticalScroll(rememberScrollState()).pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
                detectDragGestures { change, _ ->
                    change.consume()
                }
            },
        verticalArrangement = Arrangement.spacedBy(20.getDimens)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                Color.Transparent,
                Color.Transparent,
                Color.Transparent
            ),
            elevation = CardDefaults.elevatedCardElevation(1.dp),
            shape = RoundedCornerShape(16.getDimens),
            modifier = Modifier.padding(10.getDimens).height(250.getDimens).fillMaxWidth()
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                AsyncImage(
                    imageUrl = "https://i.pravatar.cc/300?u=Shushant",
                    key = "Shushant",
                    contentDescription = null,
                    modifier = Modifier.size(58.getDimens).clip(CircleShape).padding(10.dp),
                    contentScale = ContentScale.Crop
                )
                TextField(
                    value = textFieldValue,
                    onValueChange = { newTextFieldValueState ->
                        textFieldValueState = newTextFieldValueState

                        val stringChangedSinceLastInvocation =
                            lastTextValue.text != newTextFieldValueState.text
                        lastTextValue = newTextFieldValueState.annotatedString

                        if (stringChangedSinceLastInvocation) {
                            viewModel.updateState(newTextFieldValueState.text)
                        }
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                    keyboardOptions = KeyboardOptions(capitalization = Sentences),
                    placeholder = { Text(text = "What's on your mind?", color = Color.LightGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Black,
                        Color.Transparent,
                        selectionColors = TextSelectionColors(Color.Gray, Color.Cyan),
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent
                    )
                )
            }

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.getDimens),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            media?.forEach {
                when (it) {
                    is MediaType.BitmapType -> {
                        Image(
                            bitmap = it.bitmap.toImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.size(100.getDimens)
                                .clip(RoundedCornerShape(24.getDimens)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    is MediaType.PathType -> {
                        it.media.preview?.toImageBitmap()?.let { it1 ->
                            Image(
                                bitmap = it1,
                                contentDescription = null,
                                modifier = Modifier.size(100.getDimens)
                                    .clip(RoundedCornerShape(24.getDimens)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

        Divider(
            modifier = Modifier.padding(vertical = 20.dp), thickness = 1.dp, color = Color.LightGray
        )

        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)) {
            Row(
                modifier = Modifier.align(Alignment.TopStart),
                horizontalArrangement = Arrangement.spacedBy(10.getDimens),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.cameraClicked() }) {
                    Icon(
                        painter = painterResource("camera_alt.xml"),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                IconButton(onClick = { viewModel.galleryClicked() }) {
                    Icon(
                        painter = painterResource("gallery.xml"),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }

    }
}
