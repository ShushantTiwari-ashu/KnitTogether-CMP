package dev.shushant.knit_together.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import dev.shushant.permission.picker.LocalMediaController


internal val LocalLocalMediaController = staticCompositionLocalOf<LocalMediaController> {
    error("Unable to get the controller")
}

fun getSpannedString(str: String, isExpanded: Boolean? = null): AnnotatedString =
    buildAnnotatedString {
        val words = str.split(" ")
        for (word in words) {
            val trimmedWord =
                word.trimEnd('.', ',', ';', ':', '!', '?', '(', ')', '[', ']', '{', '}')
            if (trimmedWord.startsWith("http") || trimmedWord.startsWith("https")) {
                pushStringAnnotation("URL", trimmedWord)
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(word)
                }
                pop()
            } else if (trimmedWord.startsWith("#")) {
                pushStringAnnotation("HASHTAG", trimmedWord)
                withStyle(
                    style = SpanStyle(
                        color = Color(0XFF7268DC),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(word)
                }
                pop()
            } else {
                append(word)
            }
            if (word != words.last()) {
                append(" ")
            }
        }
        isExpanded?.let {
            withStyle(SpanStyle(color = Color(0xff1d11bd))) {
                if (isExpanded) {
                    pushStringAnnotation("SHOW_LESS", "SHOW_LESS")
                    append("   Show Less")
                } else {
                    pushStringAnnotation("SHOW_MORE", "SHOW_MORE")
                    append("... Show More")
                }
            }
        }
    }

