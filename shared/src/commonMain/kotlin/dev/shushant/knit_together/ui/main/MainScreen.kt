package dev.shushant.knit_together.ui.main

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import dev.shushant.knit_together.ui.authenticate.AuthenticateScreen
import dev.shushant.knit_together.ui.createpost.CreatePost
import dev.shushant.knit_together.ui.home.HomePage
import dev.shushant.knit_together.ui.notification.NotificationScreen
import dev.shushant.knit_together.ui.onboarding.OnBoardingScreen
import dev.shushant.knit_together.ui.profile.ProfileScreen
import dev.shushant.knit_together.ui.search.SearchScreen
import dev.shushant.knit_together.ui.signin.SignInScreen
import dev.shushant.knit_together.ui.signup.SignUpScreen
import dev.shushant.knit_together.ui.splash.SplashScreen
import dev.shushant.knit_together.utils.BackHandler
import dev.shushant.knit_together.utils.CommonSnackBar
import dev.shushant.knit_together.utils.KnitTogetherBottomNavBar
import dev.shushant.utils.navigation.AppState
import dev.shushant.utils.navigation.Screens
import dev.shushant.utils.navigation.TopLevelDestination
import dev.shushant.utils.navigation.getInitialScreen
import dev.shushant.utils.navigation.rememberAppState
import dev.shushant.utils.navigation.snackbarHostState
import dev.shushant.utils.theme.SafeArea
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.ui.viewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class
)
@Composable
internal fun MainScreen(
    modifier: Modifier, appState: AppState = rememberAppState()
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val viewModel = viewModel(MainViewModel::class) { MainViewModel() }
    val state by viewModel.state.collectAsState()
    val initialScreen = state.isLoggedIn.getInitialScreen

    BackHandler {
        appState.navigator.popBackStack()
    }

    Scaffold(containerColor = Color.Transparent,
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                val (x, y) = dragAmount
                when {
                    x > 0 -> {
                        //Right
                    }

                    x < 0 -> {
                        //Left
                    }
                }
                when {
                    y > 0 -> { /* down */
                    }

                    y < 0 -> { /* up */
                    }
                }
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        },
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { CommonSnackBar(snackbarHostState = snackbarHostState) },
        bottomBar = {
            if (appState.showNavIcon()) {
                KnitTogetherBottomNavBar(appState)
            }

        }) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(
                top = SafeArea.current.value.calculateTopPadding(),
            ).consumeWindowInsets(padding)
        ) {
            appState.showNavIcon().takeIf { it }?.let {
                AppBar(
                    isTopLevel = appState.showPost().not(),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                    onNavigation = {
                        appState.navigator.popBackStack()
                    },
                    onSearch = {

                    },
                    onAllLocation = {},
                    onPostClick = appState::createPost
                )
            }

            NavHost(
                navigator = appState.navigator, initialRoute = initialScreen
            ) {
                scene(Screens.Splash.route) {
                    SplashScreen(appState)
                }
                TopLevelDestination.values().forEach { screen ->
                    scene(screen.name) {
                        when (screen) {
                            TopLevelDestination.HOME -> HomePage(
                                appState,
                                viewModel.state.value.postData
                            )

                            TopLevelDestination.SEARCH -> SearchScreen(appState)
                            TopLevelDestination.CREATE -> CreatePost(
                                appState,
                                viewModel::updatePostData
                            )

                            TopLevelDestination.NOTIFICATION -> NotificationScreen(appState)
                            TopLevelDestination.PROFILE -> ProfileScreen(appState)
                        }
                    }
                }
                scene(Screens.OnBoardingScreen.route) {
                    OnBoardingScreen(appState, state.isLoggedIn)
                }
                scene(Screens.AuthenticateScreen.route) {
                    AuthenticateScreen(appState)
                }

                scene(Screens.SignIn.route) {
                    SignInScreen(appState)
                }

                scene(Screens.SignUp.route) {
                    SignUpScreen(appState)
                }

            }
        }
    }
}


