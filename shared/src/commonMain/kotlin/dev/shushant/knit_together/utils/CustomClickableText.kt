package dev.shushant.knit_together.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dev.shushant.utils.dimens.getDimens

@Composable
internal fun CustomClickableText(
    value: String, spannableString: String, tag: String = "navigate", onClick: () -> Unit
) {
    val text = buildAnnotatedString {
        val startIndex = value.indexOf(spannableString)
        val endIndex = startIndex + spannableString.length
        append(value)
        addStyle(
            SpanStyle(
                color = Color(0XFF7268DC),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal
            ), start = startIndex, end = endIndex
        )
        addStringAnnotation(
            tag = tag, annotation = toString(), start = startIndex, end = endIndex
        )
    }
    ClickableText(
        text,
        style = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Normal),
        modifier = Modifier.padding(vertical = 10.getDimens),
        onClick = {
            text.getStringAnnotations(tag, it, it).firstOrNull()?.let {
                onClick.invoke()
            }
        }
    )
}