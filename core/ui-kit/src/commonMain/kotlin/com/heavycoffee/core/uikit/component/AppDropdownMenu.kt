package com.heavycoffee.core.uikit.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heavycoffee.core.uikit.theme.AppTheme
import com.heavycoffee.core.uikit.tools.PreviewBox
import com.heavycoffee.core.uikit.tools.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>AppDropdownMenu(
    label: String,
    selectedItem: Item<T>,
    items: List<Item<T>>,
    onSelectItem: (Item<T>) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
            value = selectedItem.name,
            enabled = isEnabled,
            onValueChange = {},
            readOnly = true,
            maxLines = 1,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 200.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.name, style = AppTheme.typography.bodyLarge) },
                    onClick = {
                        onSelectItem(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

data class Item<T>(
    val data: T,
    val name: String
)

@ThemePreviews
@Composable
private fun Preview() = PreviewBox(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopStart
) {
    AppDropdownMenu(
        label = "Test",
        selectedItem = Item(
            data = "",
            name = "Test_1"
        ),
        items = listOf(
            Item(
                data = "",
                name = "Test_1"
            ),
            Item(
                data = "",
                name = "Test_2"
            ),
            Item(
                data = "",
                name = "Test_3"
            )
        ),
        onSelectItem = {}
    )
}