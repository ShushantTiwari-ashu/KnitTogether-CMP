package dev.shushant.permission.ios

import dev.shushant.permission.GalleryPermissionDelegate
import dev.shushant.permission.Permission
import dev.shushant.permission.PermissionDelegate
import dev.shushant.permission.PermissionState
import dev.shushant.permission.PermissionsControllerProtocol
import dev.shushant.permission.data.Image
import platform.AVFoundation.AVMediaTypeVideo
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

class PermissionsController : PermissionsControllerProtocol {
    override suspend fun providePermission(permission: Permission) {
        return getDelegate(permission).providePermission()
    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean {
        return getDelegate(permission).getPermissionState() == PermissionState.Granted
    }

    override suspend fun getPermissionState(permission: Permission): PermissionState {
        return getDelegate(permission).getPermissionState()
    }

    override fun openAppSettings() {
        val settingsUrl: NSURL = NSURL.URLWithString(UIApplicationOpenSettingsURLString)!!
        UIApplication.sharedApplication.openURL(settingsUrl)
    }

    private fun getDelegate(permission: Permission): PermissionDelegate {
        return when (permission) {
            Permission.REMOTE_NOTIFICATION -> RemoteNotificationPermissionDelegate()
            Permission.CAMERA -> AVCapturePermissionDelegate(AVMediaTypeVideo, permission)
            Permission.GALLERY -> GalleryPermissionDelegate()
            Permission.STORAGE, Permission.WRITE_STORAGE -> AlwaysGrantedPermissionDelegate()
        }
    }
}
