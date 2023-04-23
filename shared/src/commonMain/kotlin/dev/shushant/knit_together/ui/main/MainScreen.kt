package dev.shushant.knit_together.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.shushant.knit_together.ui.authenticate.AuthenticateScreen
import dev.shushant.knit_together.ui.onboarding.OnBoardingScreen
import dev.shushant.knit_together.ui.signin.SignInScreen
import dev.shushant.knit_together.ui.splash.SplashScreen
import dev.shushant.knit_together.utils.BackHandler
import dev.shushant.knit_together.utils.LocalMediaPickerController
import dev.shushant.permission.Permission
import dev.shushant.permission.data.AppBitmap
import dev.shushant.permission.picker.MediaSource
import dev.shushant.resource.dimens.getDimens
import dev.shushant.resource.navigation.Backstack
import dev.shushant.resource.navigation.Navigator
import dev.shushant.resource.navigation.Screens
import dev.shushant.resource.navigation.getInitialScreen
import dev.shushant.resource.theme.SafeArea
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun MainScreen(
    modifier: Modifier,
) {
    val mediaPickerController = LocalMediaPickerController.current
    val coroutineScope = rememberCoroutineScope()
    val initialScreen = getInitialScreen
    var images by remember { mutableStateOf<AppBitmap?>(null) }
    var backstack: List<Screens> by remember { mutableStateOf(listOf(initialScreen)) }
    val navigator = remember {
        Navigator(
            push = { backstack += it },
            pop = { backstack = backstack.dropLast(1) }
        )
    }
    BackHandler {
        if (backstack.isNotEmpty()) {
            navigator.pop()
        }
    }
    LaunchedEffect(Unit) {
        mediaPickerController.permissionsController.providePermission(Permission.GALLERY)
    }

    Scaffold(containerColor = Color.Transparent,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {

        }) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(
                top = SafeArea.current.value.calculateTopPadding() + 20.getDimens,
                bottom = SafeArea.current.value.calculateBottomPadding()
            ).consumeWindowInsets(padding)
        ) {
            /*if (false) {
                AppBar(titleRes = appState.currentDestination,
                    isTopLevel = !appState.showNavIcon(),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                    onNavigation = {
                        navigator.pop()
                    },
                    onSearch = {
                        navigator.push(Screens.Home)
                    },
                    onAllLocation = {})
            }*/
            Backstack(
                backstack,
            ) { screen ->
                when (screen) {
                    Screens.Splash -> SplashScreen(navigator)
                    Screens.Home -> Dummy(images) {
                        coroutineScope.launch {
                            try {
                                images = mediaPickerController.pickImage(MediaSource.GALLERY)
                            } catch (e: Exception) {
                                println(e.message)
                            }
                        }
                    }

                    Screens.OnBoardingScreen -> {
                        OnBoardingScreen(navigator)
                    }

                    Screens.AuthenticateScreen -> {
                        AuthenticateScreen(navigator)
                    }

                    Screens.SignIn -> {
                        SignInScreen(navigator)
                    }

                    else -> {}
                }
            }
        }
    }
}

@Composable
internal fun Dummy(bitmap: AppBitmap?, function: () -> Unit) {
    LaunchedEffect(Unit) {
        function.invoke()
    }
    bitmap?.let {
        Image(it.toImageBitmap(), contentDescription = null)
    }
}