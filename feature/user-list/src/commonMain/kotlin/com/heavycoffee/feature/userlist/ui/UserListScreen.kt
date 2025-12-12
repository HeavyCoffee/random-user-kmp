package com.heavycoffee.feature.userlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heavycoffee.core.uikit.component.AppBar
import com.heavycoffee.core.uikit.resources.Res
import com.heavycoffee.core.uikit.resources.ic_add_user
import com.heavycoffee.core.uikit.resources.user_list_empty_state
import com.heavycoffee.core.uikit.resources.user_list_title
import com.heavycoffee.core.uikit.tools.ThemePreviews
import com.heavycoffee.feature.userlist.component.UserListComponent
import com.heavycoffee.feature.userlist.component.UserListComponentPreviewProvider
import com.heavycoffee.feature.userlist.ui.view.UserItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@ThemePreviews
@Composable
fun UserListScreen(
    @PreviewParameter(UserListComponentPreviewProvider::class)
    component: UserListComponent
) {
    val viewState by component.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(title = stringResource(Res.string.user_list_title))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { component.onAddNewUserClick() }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add_user),
                    contentDescription = "Add user icon"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (viewState.users.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(Res.string.user_list_empty_state),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                items(
                    items = viewState.users,
                    key = { it.id }
                ) { item ->
                    UserItem(
                        fullName = item.name.fullName,
                        cell = item.cell,
                        nationality = item.nat,
                        userImageUrl = item.picture.medium,
                        onClick = { component.onUserItemClick(item.id) }
                    )
                }
            }
        }
    }
}