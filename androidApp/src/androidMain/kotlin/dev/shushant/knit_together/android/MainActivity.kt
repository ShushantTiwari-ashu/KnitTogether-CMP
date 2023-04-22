package dev.shushant.knit_together.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dev.shushant.knit_together.view.AndroidView
import dev.shushant.permission.PermissionsController
import dev.shushant.permission.picker.MediaPickerController

class MainActivity : AppCompatActivity() {

    private val mediaPickerController = MediaPickerController(PermissionsController(this))

    /*val resultApi =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            permissionController.onDataReceived(
                0,
                it.keys.toTypedArray(),
                booleanArrayToIntArray(it.values)
            )
        }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            AndroidView(mediaPickerController)
        }
        mediaPickerController.bind(lifecycle, supportFragmentManager)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mediaPickerController.permissionsController.onDataReceived(
            requestCode,
            permissions,
            grantResults
        )
    }
}

private fun booleanArrayToIntArray(values: Collection<@JvmSuppressWildcards Boolean>): IntArray {
    val intArray = IntArray(values.size)
    for (i in values.indices) {
        intArray[i] = if (values.elementAt(index = i)) 1 else 0
    }
    return intArray
}
