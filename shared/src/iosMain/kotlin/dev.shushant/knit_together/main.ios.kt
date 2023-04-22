package dev.shushant.knit_together

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import dev.shushant.resource.theme.Platform
import dev.shushant.resource.theme.PlatformState
import dev.shushant.resource.theme.darkmodeState
import org.jetbrains.skiko.SystemTheme
import org.jetbrains.skiko.currentSystemTheme
import dev.shushant.resource.theme.safeAreaState
import platform.CoreGraphics.CGFloat

internal var boundss: IntSize? = null

fun MainViewController() =
    ComposeUIViewController {
        AppViewIOS(bounds = boundss)
    }


fun setSafeArea(
    start: CGFloat,
    top: CGFloat,
    end: CGFloat,
    bottom: CGFloat,
    width: CGFloat,
    height: CGFloat
) {
    boundss = IntSize(width.toInt(), height.toInt())
    safeAreaState.value = PaddingValues(start.dp, top.dp, end.dp, bottom.dp)
    PlatformState.value = Platform.IOS
}

fun setDarkMode() {
    darkmodeState.value = currentSystemTheme == SystemTheme.DARK
}