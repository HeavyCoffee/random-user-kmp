package com.heavycoffee.feature.newuser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heavycoffee.core.uikit.component.AppBar
import com.heavycoffee.core.uikit.component.AppDropdownMenu
import com.heavycoffee.core.uikit.resources.Res
import com.heavycoffee.core.uikit.resources.general_error
import com.heavycoffee.core.uikit.resources.new_user_gender_hint
import com.heavycoffee.core.uikit.resources.new_user_generate_btn
import com.heavycoffee.core.uikit.resources.new_user_nationality_hint
import com.heavycoffee.core.uikit.resources.new_user_title
import com.heavycoffee.core.uikit.theme.AppTheme
import com.heavycoffee.core.uikit.tools.ThemePreviews
import com.heavycoffee.feature.newuser.component.NewUserComponent
import com.heavycoffee.feature.newuser.component.NewUserComponentPreviewProvider
import org.jetbrains.compose.resources.stringResource

@ThemePreviews
@Composable
fun NewUserScreen(
    @PreviewParameter(NewUserComponentPreviewProvider::class)
    component: NewUserComponent
) {
    val viewState by component.state.collectAsStateWithLifecycle()
    val errorString = stringResource(Res.string.general_error)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        component.effect.collect { effect ->
            when (effect) {
                NewUserComponent.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(errorString)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(Res.string.new_user_title),
                onBack = { component.onBackClick() },
                isNavigationBtnEnabled = !viewState.isStartComponent
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val componentModifier = Modifier.fillMaxWidth()

            AppDropdownMenu(
                modifier = componentModifier,
                isEnabled = !viewState.isLoading,
                label = stringResource(Res.string.new_user_gender_hint),
                items = viewState.genders,
                selectedItem = viewState.selectedGender,
                onSelectItem = { item ->
                    component.onGenderChange(item)
                }
            )

            AppDropdownMenu(
                modifier = componentModifier,
                isEnabled = !viewState.isLoading,
                label = stringResource(Res.string.new_user_nationality_hint),
                items = viewState.nationalities,
                selectedItem = viewState.selectedNationality,
                onSelectItem = { item ->
                    component.onNationalityChange(item)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = componentModifier.size(56.dp),
                onClick = { component.onGenerateClick() }
            ) {
                if (viewState.isLoading) {
                    CircularProgressIndicator(
                        color = AppTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(
                        text = stringResource(Res.string.new_user_generate_btn)
                    )
                }
            }
        }
    }
}