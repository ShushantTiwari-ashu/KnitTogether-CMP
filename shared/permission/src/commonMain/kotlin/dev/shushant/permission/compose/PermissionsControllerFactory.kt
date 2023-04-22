package dev.shushant.permission.compose

import androidx.compose.runtime.Composable
import dev.shushant.permission.PermissionsController

fun interface PermissionsControllerFactory {
    fun createPermissionsController(): PermissionsController
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Composable
expect fun rememberPermissionsControllerFactory(): PermissionsControllerFactory
