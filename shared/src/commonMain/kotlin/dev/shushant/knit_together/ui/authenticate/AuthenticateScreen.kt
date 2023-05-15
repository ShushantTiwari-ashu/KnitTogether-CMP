package dev.shushant.knit_together.ui.authenticate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.shushant.knit_together.utils.CustomClickableText
import dev.shushant.knit_together.utils.CustomDivider
import dev.shushant.knit_together.utils.GradientButton
import dev.shushant.utils.dimens.getDimens
import dev.shushant.utils.navigation.AppState
import dev.shushant.utils.navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun AuthenticateScreen(navigator: AppState) {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.getDimens)) {
        Column(
            modifier = Modifier.align(Alignment.Center).wrapContentHeight().verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Image(
                painter = painterResource("authenticate.png"), contentDescription = null,
                modifier = Modifier.heightIn(max = 300.dp),
                contentScale = ContentScale.Inside
            )

            GradientButton(modifier = Modifier.height(50.dp),icon = "google.png", text = "Login with Google") {

            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(50.dp)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(16.getDimens)
                    ),
                shape = RoundedCornerShape(16.getDimens),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
            ) {
                Text(
                    "Sign up with email or phone number",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier
                )
            }

            CustomDivider()
            CustomClickableText(
                value = "Already have an account? SignIn",
                spannableString = "SignIn"
            ) {
                navigator.navigate(Screens.SignIn.route)
            }
        }
    }
}