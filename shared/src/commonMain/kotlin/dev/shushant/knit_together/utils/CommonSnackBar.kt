package dev.shushant.knit_together.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.shushant.utils.dimens.getDimens
import dev.shushant.utils.theme.SafeArea

@Composable
fun CommonSnackBar(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        snackbarHostState,
        modifier = Modifier
            .padding(
                PaddingValues(
                    top = SafeArea.current.value.calculateTopPadding(),
                    bottom = SafeArea.current.value.calculateBottomPadding() + 30.dp,
                    start = 30.dp,
                    end = 30.dp
                )
            )
    ) {
        Snackbar(
            it,
            containerColor = Color.White,
            contentColor = Color.Black,
            actionContentColor = Color.Transparent,
            actionColor = Color.Transparent,
            dismissActionContentColor = Color.Transparent
        )
    }
}