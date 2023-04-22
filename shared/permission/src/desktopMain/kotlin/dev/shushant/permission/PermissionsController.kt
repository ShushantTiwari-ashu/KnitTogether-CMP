package dev.shushant.permission

actual interface PermissionsController {

    actual suspend fun providePermission(permission: Permission)


    actual suspend fun isPermissionGranted(permission: Permission): Boolean


    actual suspend fun getPermissionState(permission: Permission): PermissionState


    actual fun openAppSettings()

    companion object {
        fun invoke(): PermissionsController = PermissionsControllerImpl()
    }

}