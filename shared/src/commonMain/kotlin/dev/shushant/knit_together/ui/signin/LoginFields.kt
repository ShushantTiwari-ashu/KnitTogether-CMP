package dev.shushant.knit_together.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.shushant.resource.extensions.textFieldColors
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalResourceApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun LoginFields(loginState: LoginState, localFocusManager: FocusManager) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = loginState.email,
        onValueChange = {},
        isError = !(loginState.isValidEmail ?: true),
        placeholder = {
            Text(
                text = "user@email.com",
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        },
        label = {
            Text(
                text = "Email or phone number",
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.None
            )
        },

        supportingText = {
            if (loginState.isValidEmail == false) {
                Text(text = "Incorrect email!", textAlign = TextAlign.Center)
            }
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
        leadingIcon = {
            Image(
                painter = painterResource("email.xml"),
                contentDescription = "Email", colorFilter = ColorFilter.tint(
                    Color.Black
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        ),
        colors = textFieldColors,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp)
    )

    OutlinedTextField(
        value = loginState.password,
        onValueChange = {},
        isError = !(loginState.isValidEmail ?: true),
        placeholder = {
            Text(
                text = "Password",
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        },
        label = {
            Text(
                text = "Password",
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.None
            )
        },

        supportingText = {
            if (loginState.isValidPassword == false) {
                Text(text = "Incorrect password!", textAlign = TextAlign.Center)
            }
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
        leadingIcon = {
            Image(
                painter = painterResource("password_leading.xml"),
                contentDescription = "Password", colorFilter = ColorFilter.tint(
                    Color.Black
                )
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(passwordVisibility.getDrawable),
                contentDescription = "Password", colorFilter = ColorFilter.tint(
                    Color.Black
                ),
                modifier = Modifier.clickable { passwordVisibility = !passwordVisibility }
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(
            '*'
        ),
        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() }),
        colors = textFieldColors,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp)
    )

}

val Boolean.getDrawable
    @Composable get() = if (this) "password.xml" else "password_hidden.xml"