package dev.shushant.resource.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import dev.shushant.resource.theme.CurrentPlatform
import dev.shushant.resource.theme.Platform

sealed class Screens(val route: String) {
    object Splash : Screens("Splash")
    object Home : Screens("Home")
    object SignIn : Screens("SignIn")
    object SignUp : Screens("SignUp")
    object OnBoardingScreen : Screens("OnBoardingScreen")
    object AuthenticateScreen : Screens("AuthenticateScreen")
}


val Boolean.getInitialScreen
    @Composable get() = if (CurrentPlatform.current.value == Platform.DESKTOP)
        Screens.Splash.route
    else
        if (this) TopLevelDestination.HOME.name else Screens.OnBoardingScreen.route


val LocalSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("No SnackbarHostState provided") }

val snackbarHostState
    @Composable
    get() = LocalSnackbarHostState.current





