package dev.shushant.knit_together.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import dev.shushant.knit_together.utils.GradientButton
import dev.shushant.resource.common.viewModelFactory
import dev.shushant.resource.dimens.getDimens
import dev.shushant.resource.navigation.Navigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalCoroutinesApi::class)
@Composable
internal fun SignInScreen(navigator: Navigator) {
    val localFocusManager = LocalFocusManager.current
    val signInViewModel = rememberSignInViewModel()
    val loginState by signInViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
            detectDragGestures { change, dragAmount ->
                change.consume()
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
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


        GradientButton(text = "Log In") {
            signInViewModel.login()
        }
    }
}

@Composable
internal fun rememberSignInViewModel(): SignInViewModel {
    return viewModelFactory { scope -> SignInViewModel(scope) }
}