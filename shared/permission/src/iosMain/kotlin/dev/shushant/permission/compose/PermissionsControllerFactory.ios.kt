package dev.shushant.permission.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.shushant.permission.ios.PermissionsController

@Composable
actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    return remember {
        PermissionsControllerFactory {
            PermissionsController()
        }
    }
}
