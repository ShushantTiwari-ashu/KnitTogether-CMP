package dev.shushant.knit_together

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import dev.shushant.resource.dimens.DeviceConfiguration
import dev.shushant.resource.theme.Platform
import dev.shushant.resource.theme.PlatformState
import dev.shushant.knit_together.ui.App
import dev.shushant.permission.picker.LocalMediaController


@Composable
fun MainView(deviceConfiguration: DeviceConfiguration) {
    PlatformState.value = Platform.DESKTOP
    App(deviceConfiguration, LocalMediaController.invoke())
}

@Preview
@Composable
fun AppPreview() {
    App(DeviceConfiguration(), LocalMediaController.invoke())
}