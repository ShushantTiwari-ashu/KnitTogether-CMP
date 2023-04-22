package dev.shushant.permission

class PermissionsControllerImpl : PermissionsController {
    override suspend fun providePermission(permission: Permission) {

    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean {
        return false
    }

    override suspend fun getPermissionState(permission: Permission): PermissionState {
        return PermissionState.Granted
    }

    override fun openAppSettings() {

    }
}