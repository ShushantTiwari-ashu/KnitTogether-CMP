package dev.shushant.permission

import dev.shushant.permission.data.Image


internal interface PermissionDelegate {
    suspend fun providePermission()
    suspend fun getPermissionState(): PermissionState

}
