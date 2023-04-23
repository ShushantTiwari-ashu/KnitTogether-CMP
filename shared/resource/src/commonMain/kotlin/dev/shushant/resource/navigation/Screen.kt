package dev.shushant.resource.navigation

import androidx.compose.runtime.Composable
import dev.shushant.resource.theme.CurrentPlatform
import dev.shushant.resource.theme.Platform

sealed class Screens {
    object Splash : Screens()
    object Home : Screens()
    object SignIn : Screens()
    object OnBoardingScreen : Screens()
    object AuthenticateScreen : Screens()
}


val getInitialScreen
    @Composable get() = if (CurrentPlatform.current.value == Platform.DESKTOP)
        Screens.Splash
    else
        Screens.OnBoardingScreen


data class Navigator(
    val push: (Screens) -> Unit,
    val pop: () -> Unit
)

