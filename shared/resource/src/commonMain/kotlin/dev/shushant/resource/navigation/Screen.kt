package dev.shushant.resource.navigation

import androidx.compose.runtime.Composable
import dev.shushant.resource.theme.CurrentPlatform
import dev.shushant.resource.theme.Platform
import moe.tlaster.precompose.navigation.PopUpTo

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

data class NavOptions(
    /**
     * Whether this navigation action should launch as single-top (i.e., there will be at most
     * one copy of a given destination on the top of the back stack).
     */
    val launchSingleTop: Boolean = false,
    /**
     * The destination to pop up to before navigating. When set, all non-matching destinations
     * should be popped from the back stack.
     * @see [PopUpTo]
     */
    val popUpTo: PopUpTo = PopUpTo.None,
)


