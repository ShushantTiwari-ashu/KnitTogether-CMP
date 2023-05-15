package dev.shushant.knit_together

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import dev.shushant.knit_together.ui.App
import dev.shushant.permission.ios.PermissionsController
import dev.shushant.permission.picker.ios.MediaPickerController
import dev.shushant.utils.dimens.DeviceConfiguration
import platform.UIKit.UIApplication

@Composable
internal fun AppViewIOS(bounds: IntSize?) {
    val size = remember { mutableStateOf(IntSize.Zero) }
    val window = UIApplication.sharedApplication.keyWindow
    val viewController = window?.rootViewController
    val permissionsController by remember {
        mutableStateOf(
            MediaPickerController(
                PermissionsController()
            )
        )
    }

    LaunchedEffect(Unit) {
        if (viewController != null) {
            permissionsController.bind(viewController = viewController)
        }
    }

    Box(Modifier.fillMaxSize().onGloballyPositioned { coordinates ->
        size.value = coordinates.size
    }) {
        App(
            deviceConfiguration = DeviceConfiguration(
                screenWidthDp = bounds?.width ?: 0,
                screenHeight = bounds?.height ?: 0
            ),
            permissionsController
        )
    }
}
