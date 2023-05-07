package dev.shushant.knit_together.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.utils.CustomClickableText
import dev.shushant.knit_together.utils.CustomDivider
import dev.shushant.knit_together.utils.GradientButton
import dev.shushant.knit_together.utils.Loader
import dev.shushant.resource.dimens.getDimens
import dev.shushant.resource.navigation.AppState
import dev.shushant.resource.navigation.Screens
import dev.shushant.resource.navigation.TopLevelDestination
import dev.shushant.resource.navigation.snackbarHostState
import moe.tlaster.precompose.ui.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SignInScreen(navigator: AppState) {
    val localFocusManager = LocalFocusManager.current
    val signInViewModel = viewModel(SignInViewModel::class) {
        SignInViewModel()
    }
    val loginState by signInViewModel.state.collectAsState()
    val snackbar = snackbarHostState
    LaunchedEffect(loginState) {
        when {
            loginState.error != null -> {
                snackbar.showSnackbar(message = loginState.error!!)
            }

            loginState.isLoggedIn -> {
                navigator.navigate(TopLevelDestination.HOME.name, popUpTo = true)
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                    detectDragGestures { change, _ ->
                        change.consume()
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.heightIn(min = 30.dp, max = 35.dp))
            Image(
                painter = painterResource("splash.png"),
                contentDescription = null,
                modifier = Modifier.size(104.getDimens)
            )

            /* Text(
                 "\"Capture your knitting memories with just one login\"",
                 modifier = Modifier.padding(horizontal = 20.dp),
                 style = MaterialTheme.typography.bodyMedium,
                 fontWeight = FontWeight.Medium,
                 color = Color.Black,
                 textAlign = TextAlign.Center
             )*/
            Text(
                "\"Welcome back, knitter! Login to keep your memories close at hand.\"",
                modifier = Modifier,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            LoginFields(loginState = loginState, localFocusManager, signInViewModel)

            GradientButton(
                enabled = !loginState.isLoading,
                modifier = Modifier.height(50.dp),
                text = "Log In"
            ) {
                signInViewModel.login()
            }

            CustomDivider()

            CustomClickableText(
                value = "Don't have an account? Sign up",
                spannableString = "Sign up"
            ) {
                navigator.navigate(Screens.SignUp.route)
            }

        }

        if (loginState.isLoading) {
            Loader(modifier = Modifier.align(Alignment.Center))
        }
    }
}