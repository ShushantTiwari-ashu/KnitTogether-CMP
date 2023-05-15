package dev.shushant.knit_together.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.shushant.knit_together.ui.main.MainScreen
import dev.shushant.knit_together.utils.LocalLocalMediaController
import dev.shushant.permission.picker.LocalMediaController
import dev.shushant.utils.dimens.DeviceConfiguration
import dev.shushant.utils.navigation.LocalSnackbarHostState
import dev.shushant.utils.theme.AppGradientBackground
import dev.shushant.utils.theme.AppTheme
import dev.shushant.utils.theme.LocalGradientColors

@Composable
fun App(
    deviceConfiguration: DeviceConfiguration, localMediaController: LocalMediaController
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    CompositionLocalProvider(
        LocalLocalMediaController provides localMediaController,
        LocalSnackbarHostState provides snackbarHostState
    ) {
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
