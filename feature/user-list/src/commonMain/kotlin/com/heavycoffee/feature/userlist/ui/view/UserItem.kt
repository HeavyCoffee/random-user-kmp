package com.heavycoffee.feature.userlist.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.heavycoffee.core.uikit.component.AppAsyncImage
import com.heavycoffee.core.uikit.theme.AppTheme
import com.heavycoffee.core.uikit.tools.PreviewBox
import com.heavycoffee.core.uikit.tools.ThemePreviews

@Composable
internal fun UserItem(
    fullName: String,
    cell: String,
    nationality: String,
    userImageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppAsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = shape),
                model = userImageUrl,
                contentDescription = "User image"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = fullName,
                    style = AppTheme.typography.titleMedium
                )
                Text(
                    text = cell,
                    style = AppTheme.typography.bodyLarge
                )
                Text(
                    text = nationality,
                    style = AppTheme.typography.bodyLarge
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() = PreviewBox {
    UserItem(
        fullName = "Test User",
        cell = "(272) 790-0888",
        nationality = "RU",
        userImageUrl = "",
        onClick = {}
    )
}