package com.heavycoffee.core.uikit.tools

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.heavycoffee.core.uikit.theme.AppTheme

@Composable
fun PreviewBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    AppTheme {
        Box(
            modifier = modifier,
            contentAlignment = contentAlignment,
            content = content
        )
    }
}