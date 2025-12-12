package com.heavycoffee.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.heavycoffee.core.uikit.theme.AppTheme
import com.heavycoffee.navigation.RootComponent
import com.heavycoffee.navigation.RootScreen

@Composable
@Preview
fun RandomUserApp(rootComponent: RootComponent) {
    AppTheme {
        RootScreen(rootComponent)
    }
}