package dev.shushant.knit_together.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.shushant.resource.extensions.textBrush
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
internal fun AppBar(
    titleRes: String = "Create Post",
    isTopLevel: Boolean,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {},
    onAllLocation: () -> Unit = {},
    onNavigation: () -> Unit = {},
    onPostClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            if (isTopLevel) {
                Image(
                    painter = painterResource("splash.png"),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            } else {
                Text(
                    text = titleRes,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        actions = {
            if (isTopLevel) {
                IconButton(
                    onSearch,
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource("chat.xml"),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            } else {
                Text(
                    text = "Post", modifier
                    = Modifier.textBrush(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF67E1D2),
                                Color(0xcc54a8ff)
                            )
                        )
                    ).clickable {
                        onPostClick.invoke()
                    }
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("AppBar").padding(horizontal = 10.dp),
        navigationIcon = {
            if (isTopLevel) {
                IconButton(
                    onAllLocation,
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource("camera_alt.xml"),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            } else {
                IconButton(
                    onNavigation,
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    )
}