package dev.shushant.permission

import dev.shushant.permission.data.Image

interface PermissionsControllerProtocol  {
    suspend fun providePermission(permission: Permission)
    suspend fun isPermissionGranted(permission: Permission): Boolean
    suspend fun getPermissionState(permission: Permission): PermissionState
    fun openAppSettings()
}
