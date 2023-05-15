package dev.shushant.utils.dimens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.shushant.utils.theme.LocalAppDimens


sealed interface Dimensions {
    object sw320 : Dimensions

    object sw480 : Dimensions

    object sw600 : Dimensions

    object sw720 : Dimensions

}


data class DeviceConfiguration(
    val screenWidthDp: Int = 0, val screenHeight: Int = 0
)

val LocalAppConfiguration = staticCompositionLocalOf {
    DeviceConfiguration()
}


/**
 * Convert a dimension to Ldpi(low-density) screens
 */
fun Float.toLdpi() = this.times(0.75).dp

/**
 * Convert a dimension to Hdpi(high-density) screens
 */
fun Float.toHdpi() = this.times(1.5).dp

/**
 * Convert a dimension to Xhdpi(extra-high-density) screens
 */
fun Float.toXhdpi() = this.times(2.0).dp


val Int.getDimens
    @Composable get() =
        when (LocalAppDimens.current) {
            Dimensions.sw320 -> this.toFloat().toLdpi()
            Dimensions.sw480 -> this.dp
            Dimensions.sw600 -> this.toFloat().toHdpi()
            Dimensions.sw720 -> this.toFloat().toXhdpi()
        }

val Float.getDimens
    @Composable get() =
        when (LocalAppDimens.current) {
            Dimensions.sw320 -> this.toLdpi()
            Dimensions.sw480 -> this.dp
            Dimensions.sw600 -> this.toHdpi()
            Dimensions.sw720 -> this.toXhdpi()
        }


val Int.getColor
    @Composable get() = Color(0XFFFFD439)