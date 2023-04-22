package dev.shushant.permission.ios

import dev.shushant.permission.PermissionDelegate
import dev.shushant.permission.PermissionState
import dev.shushant.permission.data.Image


internal class AlwaysGrantedPermissionDelegate : PermissionDelegate {
    override suspend fun providePermission() = Unit

    override suspend fun getPermissionState(): PermissionState = PermissionState.Granted
}
