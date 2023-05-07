package dev.shushant.knit_together.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
internal fun PostBodyText(annotatedDescription: AnnotatedString, modifier: Modifier) {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }

    ReadMoreText(
        text = annotatedDescription,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
            .animateContentSize(animationSpec = tween(durationMillis = 100, delayMillis = 10)),
        color = Color.Black,
        style = MaterialTheme.typography.bodyLarge,
        readMoreText = "See more",
        readMoreColor = Color(0XFF7268DC),
        readMoreFontSize = 14.sp,
        readMoreFontWeight = FontWeight.Bold,
        readMoreFontStyle = FontStyle.Italic,
        readMoreTextDecoration = TextDecoration.Underline,
        readLessText = "See less",
        toggleArea = ToggleArea.More,
        readMoreMaxLines = 3
    )

}