package com.heavycoffee.core.uikit.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.heavycoffee.core.uikit.resources.Res
import com.heavycoffee.core.uikit.resources.ic_chevron_left
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    isNavigationBtnEnabled: Boolean = false,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (isNavigationBtnEnabled) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_chevron_left),
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview() = AppBar(
    title = "Test title",
    onBack = {}
)