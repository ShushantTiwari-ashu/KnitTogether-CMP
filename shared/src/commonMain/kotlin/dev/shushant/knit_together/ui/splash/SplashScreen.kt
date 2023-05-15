package dev.shushant.knit_together.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import dev.shushant.utils.dimens.getDimens
import dev.shushant.utils.navigation.AppState
import dev.shushant.utils.navigation.Screens
import dev.shushant.utils.navigation.TopLevelDestination
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SplashScreen(appState: AppState) {
    LaunchedEffect(Unit) {
        delay(2000L)
        appState.navigate(TopLevelDestination.HOME.name, popUpTo = true)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource("splash.png"),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).padding(20.getDimens).fillMaxSize(0.5f),
            contentScale = ContentScale.Inside
        )
    }
}