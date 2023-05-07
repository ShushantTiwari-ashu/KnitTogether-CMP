package dev.shushant.knit_together.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import dev.shushant.resource.dimens.getDimens

@Composable
internal fun CustomDivider() {
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
}