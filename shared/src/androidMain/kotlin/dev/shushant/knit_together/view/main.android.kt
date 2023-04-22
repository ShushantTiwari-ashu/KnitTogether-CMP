package dev.shushant.knit_together.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.shushant.knit_together.ui.App
import dev.shushant.permission.picker.MediaPickerController
import dev.shushant.resource.dimens.DeviceConfiguration
import dev.shushant.resource.theme.Platform
import dev.shushant.resource.theme.PlatformState

@Composable
fun AndroidView(mediaPickerController: MediaPickerController) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val configuration = LocalConfiguration.current
    PlatformState.value = Platform.ANDROID

    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        onDispose {}
    }
    Column {
        App(
            deviceConfiguration = DeviceConfiguration(
                configuration.screenWidthDp,
                configuration.screenHeightDp
            ),
            mediaPickerController = mediaPickerController
        )
    }
}