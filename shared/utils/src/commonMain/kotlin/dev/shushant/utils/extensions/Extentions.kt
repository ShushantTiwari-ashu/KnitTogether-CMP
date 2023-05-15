package dev.shushant.utils.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import dev.shushant.utils.theme.CircleStrokeColor
import dev.shushant.utils.theme.GradientEnd
import dev.shushant.utils.theme.GradientStart

fun Modifier.textBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }

fun getDate(): Pair<String, String> {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val startOfWeek =
        today.minus(DateTimeUnit.DayBased(today.dayOfWeek.ordinal - DayOfWeek.MONDAY.ordinal))
    val endOfWeek =
        today.plus(DateTimeUnit.DayBased(DayOfWeek.SUNDAY.ordinal - today.dayOfWeek.ordinal))

    return Pair(startOfWeek.toString(), endOfWeek.toString())
}

fun LocalDate.isToday() =
    this.toString() == Clock.System.todayIn(TimeZone.currentSystemDefault()).toString()

val textColor: Color
    @Composable
    @ReadOnlyComposable
    get() = if (isSystemInDarkTheme())
        Color.Black
    else
        Color.Black


@OptIn(ExperimentalMaterial3Api::class)
val textFieldColors
    @Composable
    get() = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.LightGray,
        focusedBorderColor = CircleStrokeColor,
        disabledBorderColor = CircleStrokeColor,
        textColor = textColor,
        disabledLabelColor = textColor,
        focusedLabelColor = textColor,
        errorLeadingIconColor = textColor,
        disabledLeadingIconColor = textColor,
        focusedLeadingIconColor = textColor,
        unfocusedLeadingIconColor = textColor,
        cursorColor = textColor
    )

val Float.brush: ShaderBrush
    @Composable
    get() = remember(this) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * this@brush
                val heightOffset = size.height * this@brush
                return LinearGradientShader(
                    colors = listOf(
                        GradientStart, GradientEnd
                    ),
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset + size.width, heightOffset + size.height),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

fun Modifier.noClickable() = then(Modifier.clickable(enabled = false) {})