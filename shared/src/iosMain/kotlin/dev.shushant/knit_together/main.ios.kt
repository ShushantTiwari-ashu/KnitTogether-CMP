package dev.shushant.knit_together

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import dev.shushant.utils.theme.Platform
import dev.shushant.utils.theme.PlatformState
import dev.shushant.utils.theme.darkmodeState
import org.jetbrains.skiko.SystemTheme
import org.jetbrains.skiko.currentSystemTheme
import dev.shushant.utils.theme.safeAreaState
import moe.tlaster.precompose.PreComposeApplication
import platform.CoreGraphics.CGFloat

internal var boundss: IntSize? = null

fun MainViewController() =
    PreComposeApplication("KnitTogether") {
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