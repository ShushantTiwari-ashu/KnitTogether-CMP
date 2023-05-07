package dev.shushant.knit_together.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.shushant.resource.dimens.getDimens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun GradientButton(
    enabled: Boolean = true,
    modifier: Modifier, colors: List<Color> = listOf(
        Color(0xb37d72e0),
        Color(0xb3c48de4),
    ), icon: String = "", text: String, onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier.background(
            Brush.linearGradient(
                colors,
            ), shape = RoundedCornerShape(16.getDimens)
        ).fillMaxWidth(),
        shape = RoundedCornerShape(16.getDimens),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Transparent,
            containerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ), enabled = enabled
    ) {
        if (icon.isNotBlank()) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        Text(
            text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}