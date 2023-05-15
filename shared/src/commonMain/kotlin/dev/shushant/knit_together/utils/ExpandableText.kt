package dev.shushant.knit_together.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextLayoutResult

const val DEFAULT_MINIMUM_TEXT_LINE = 3

@Composable
fun ExpandingText(modifier: Modifier = Modifier, text: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }
    val textLayoutResult = textLayoutResultState.value
    val uriHandler = LocalUriHandler.current
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = text
            }

            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(DEFAULT_MINIMUM_TEXT_LINE - 1)
                val showMoreString = "... Show More"
                finalText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length).dropLastWhile { it == ' ' || it == '.' }

                isClickable = true
            }
        }
    }
    val str = getSpannedString(str = finalText, isExpanded = isExpanded)

    ClickableText(text = str,
        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
        maxLines = if (isExpanded) Int.MAX_VALUE else DEFAULT_MINIMUM_TEXT_LINE,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = modifier.animateContentSize(),
        onClick = {
            str.getStringAnnotations("URL", it, it).firstOrNull()?.let { annotation ->
                // Handle URL click event here
                uriHandler.openUri(annotation.item)
            }
            str.getStringAnnotations("SHOW_LESS", it, it).firstOrNull()?.let {
                isExpanded = !isExpanded
            }
            str.getStringAnnotations("SHOW_MORE", it, it).firstOrNull()?.let {
                isExpanded = !isExpanded
            }
            str.getStringAnnotations("HASHTAG", it, it).firstOrNull()?.let { annotation ->
                // Handle hashtag click event here
            }
        })
}
