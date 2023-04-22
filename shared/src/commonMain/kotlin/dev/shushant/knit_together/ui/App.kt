package dev.shushant.knit_together.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import dev.shushant.knit_together.ui.main.MainScreen
import dev.shushant.knit_together.utils.LocalMediaPickerController
import dev.shushant.permission.picker.MediaPickerController
import dev.shushant.resource.dimens.DeviceConfiguration
import dev.shushant.resource.theme.AppGradientBackground
import dev.shushant.resource.theme.AppTheme
import dev.shushant.resource.theme.LocalGradientColors

@Composable
fun App(
    deviceConfiguration: DeviceConfiguration, mediaPickerController: MediaPickerController
) {
    CompositionLocalProvider(LocalMediaPickerController provides mediaPickerController) {
        AppTheme(
            deviceConfiguration = deviceConfiguration,
        ) {
            AppGradientBackground(
                gradientColors = LocalGradientColors.current
            ) {
                MainScreen(modifier = Modifier)
            }
        }
    }
}
