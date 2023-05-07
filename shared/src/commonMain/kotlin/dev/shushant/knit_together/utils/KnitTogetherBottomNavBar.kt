package dev.shushant.knit_together.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.shushant.resource.common.AsyncImage
import dev.shushant.resource.navigation.AppState
import dev.shushant.resource.navigation.TopLevelDestination
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun KnitTogetherBottomNavBar(appState: AppState) {
    NavigationBar(
        containerColor = Color(0x1a6b65de),
        tonalElevation = 0.dp,
        windowInsets = WindowInsets(0, 10, 0, 0)
    ) {
        TopLevelDestination.values().forEach { destination ->
            val selected =
                appState.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    BottomNavImage(icon, destination)
                },
                label = { },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    unselectedTextColor = Color.Transparent,
                    selectedTextColor = Color.Transparent,
                    unselectedIconColor = Color.Transparent,
                    selectedIconColor = Color.Transparent
                ),
                onClick = {
                    appState.navigate(destination.name, true)
                },
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomNavImage(icon: String, currentDestination: TopLevelDestination) {
    if (currentDestination == TopLevelDestination.PROFILE) {
        Box(
            modifier = Modifier.clip(CircleShape).size(24.dp).border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xb37d72e0),
                        Color(0xb3c48de4),
                    )
                ),
                shape = CircleShape

            )
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(22.dp).padding(2.dp)
            )
            AsyncImage(
                imageUrl = "https://i.pravatar.cc/300?u=Shushant",
                key = "Shushant",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    } else {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp).padding(2.dp)
        )
    }
}
