package dev.shushant.knit_together

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dev.shushant.knit_together.view.AndroidView
import dev.shushant.permission.picker.LocalMediaController
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {

    private val localMediaController = LocalMediaController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            AndroidView(localMediaController)
        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        localMediaController.permissionsController.onDataReceived(
            requestCode,
            permissions,
            grantResults
        )
    }

    @Suppress("DEPRECATION")
    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onActivityResult(requestCode, resultCode, data)",
            "androidx.appcompat.app.AppCompatActivity"
        )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        localMediaController.onActivityResult(requestCode, resultCode, data)
    }
}


private fun booleanArrayToIntArray(values: Collection<@JvmSuppressWildcards Boolean>): IntArray {
    val intArray = IntArray(values.size)
    for (i in values.indices) {
        intArray[i] = if (values.elementAt(index = i)) 1 else 0
    }
    return intArray
}