package dev.shushant.knit_together.ui.authenticate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.shushant.resource.dimens.getDimens
import dev.shushant.resource.navigation.Navigator
import dev.shushant.resource.navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AuthenticateScreen(navigator: Navigator) {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.getDimens)) {
        Column(
            modifier = Modifier.align(Alignment.Center).wrapContentHeight().verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.getDimens)
        ) {
            Image(
                painter = painterResource("authenticate.png"), contentDescription = null
            )

            Button(
                onClick = {},
                modifier = Modifier.background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xb37d72e0),
                            Color(0xb3c48de4),
                        ),
                    ), shape = RoundedCornerShape(16.getDimens)
                ).fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(16.getDimens),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
            ) {
                Icon(
                    painter = painterResource("google.png"),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    "Login with Google",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(16.getDimens),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Transparent,
                    containerColor = Color(0XFFF6F2F6),
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

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier.background(color = Color.LightGray).weight(0.4f)
                        .height(1.getDimens)
                )
                Text(
                    "OR",
                    modifier = Modifier.weight(0.2f),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Spacer(
                    modifier = Modifier.background(color = Color.LightGray).weight(0.4f)
                        .height(1.getDimens)
                )
            }

            val text = buildAnnotatedString {
                val str = "Already have an account? SignIn"
                val startIndex = str.indexOf("SignIn")
                val endIndex = startIndex + 6
                append(str)
                addStyle(SpanStyle(color = Color(0XFF7268DC)), start = startIndex, end = endIndex)
                addStringAnnotation(
                    tag = "navigate",
                    annotation = Screens.SignIn.toString(),
                    start = startIndex,
                    end = endIndex
                )
            }
            ClickableText(text,
                style = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Normal),
                modifier = Modifier.padding(vertical = 10.getDimens),
                onClick = {
                    text.getStringAnnotations("navigate", it, it).firstOrNull()
                        ?.let {
                            navigator.push(Screens.SignIn)
                        }
                })
        }
    }
}