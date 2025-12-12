package com.heavycoffee.core.uikit.component

import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.heavycoffee.core.uikit.theme.AppTheme

@Composable
fun AppTab(
    isSelected: Boolean,
    onClick: () -> Unit,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Tab(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = AppTheme.colorScheme.onSurface,
        unselectedContentColor = AppTheme.colorScheme.onSurface.copy(alpha = .5f)
    ) {
        Icon(
            painter = painter,
            contentDescription = "Tab icon"
        )
    }
}