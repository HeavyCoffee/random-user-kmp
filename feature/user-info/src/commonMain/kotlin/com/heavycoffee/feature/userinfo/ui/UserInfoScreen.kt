package com.heavycoffee.feature.userinfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heavycoffee.core.uikit.component.AppAsyncImage
import com.heavycoffee.core.uikit.component.AppBar
import com.heavycoffee.core.uikit.component.AppTab
import com.heavycoffee.core.uikit.resources.Res
import com.heavycoffee.core.uikit.resources.general_error
import com.heavycoffee.core.uikit.resources.ic_email
import com.heavycoffee.core.uikit.resources.ic_location
import com.heavycoffee.core.uikit.resources.ic_phone
import com.heavycoffee.core.uikit.resources.ic_user
import com.heavycoffee.core.uikit.resources.user_info_age_label
import com.heavycoffee.core.uikit.resources.user_info_cell_label
import com.heavycoffee.core.uikit.resources.user_info_city_label
import com.heavycoffee.core.uikit.resources.user_info_date_of_birth_label
import com.heavycoffee.core.uikit.resources.user_info_email_label
import com.heavycoffee.core.uikit.resources.user_info_first_name_label
import com.heavycoffee.core.uikit.resources.user_info_gender_label
import com.heavycoffee.core.uikit.resources.user_info_last_name_label
import com.heavycoffee.core.uikit.resources.user_info_phone_label
import com.heavycoffee.core.uikit.resources.user_info_postcode_label
import com.heavycoffee.core.uikit.resources.user_info_state_label
import com.heavycoffee.core.uikit.resources.user_info_street_label
import com.heavycoffee.core.uikit.resources.user_info_timezone_label
import com.heavycoffee.core.uikit.theme.AppTheme
import com.heavycoffee.core.uikit.tools.ThemePreviews
import com.heavycoffee.feature.userinfo.component.UserInfoComponent
import com.heavycoffee.feature.userinfo.component.UserInfoComponent.State
import com.heavycoffee.feature.userinfo.component.UserInfoComponent.State.UserInfoTabEnum
import com.heavycoffee.feature.userinfo.component.UserInfoComponentPreviewProvider
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@ThemePreviews
@Composable
fun UserInfoScreen(
    @PreviewParameter(UserInfoComponentPreviewProvider::class)
    component: UserInfoComponent
) {
    val viewState by component.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            AppBar(
                title = "",
                isNavigationBtnEnabled = true,
                onBack = { component.onBackClick() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!viewState.isError) {
                ContentState(viewState, { component.onTabClick(it) })
            } else {
                ErrorState()
            }
        }
    }
}

@Composable
private fun ContentState(
    viewState: State,
    onTabClick: (UserInfoTabEnum) -> Unit
) {
    with(viewState) {
        AppAsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            model = userAvatarUrl,
            contentDescription = "User image"
        )

        Text(
            text = "$firstName $lastName",
            style = AppTheme.typography.titleLarge
        )

        val maxWithModifier = Modifier.fillMaxWidth()

        Column(
            modifier = maxWithModifier
                .padding(bottom = 40.dp)
                .fillMaxSize()
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = AppTheme.colorScheme.secondaryContainer
                )
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = maxWithModifier
            ) {
                val tabModifier = Modifier
                    .weight(1f)
                    .height(48.dp)

                AppTab(
                    modifier = tabModifier,
                    isSelected = selectedTab == UserInfoTabEnum.MAIN_INFO,
                    onClick = { onTabClick(UserInfoTabEnum.MAIN_INFO) },
                    painter = painterResource(Res.drawable.ic_user)
                )
                AppTab(
                    modifier = tabModifier,
                    isSelected = selectedTab == UserInfoTabEnum.PHONES,
                    onClick = { onTabClick(UserInfoTabEnum.PHONES) },
                    painter = painterResource(Res.drawable.ic_phone)
                )
                AppTab(
                    modifier = tabModifier,
                    isSelected = selectedTab == UserInfoTabEnum.EMAIL,
                    onClick = { onTabClick(UserInfoTabEnum.EMAIL) },
                    painter = painterResource(Res.drawable.ic_email)
                )
                AppTab(
                    modifier = tabModifier,
                    isSelected = selectedTab == UserInfoTabEnum.LOCATION,
                    onClick = { onTabClick(UserInfoTabEnum.LOCATION) },
                    painter = painterResource(Res.drawable.ic_location)
                )
            }
            when (selectedTab) {
                UserInfoTabEnum.MAIN_INFO -> {
                    Text(text = stringResource(Res.string.user_info_first_name_label, firstName))
                    Text(text = stringResource(Res.string.user_info_last_name_label, lastName))
                    Text(text = stringResource(Res.string.user_info_gender_label, gender))
                    Text(text = stringResource(Res.string.user_info_age_label, age))
                    Text(text = stringResource(Res.string.user_info_date_of_birth_label, daeOfBirth))
                }
                UserInfoTabEnum.PHONES -> {
                    Text(text = stringResource(Res.string.user_info_phone_label, phone))
                    Text(text = stringResource(Res.string.user_info_cell_label, cell))
                }
                UserInfoTabEnum.EMAIL -> {
                    Text(text = stringResource(Res.string.user_info_email_label, email))
                }
                UserInfoTabEnum.LOCATION -> {
                    Text(text = stringResource(Res.string.user_info_street_label, street))
                    Text(text = stringResource(Res.string.user_info_city_label, city))
                    Text(text = stringResource(Res.string.user_info_state_label, state))
                    Text(text = stringResource(Res.string.user_info_postcode_label, postcode))
                    Text(text = stringResource(Res.string.user_info_timezone_label, timezone))
                }
            }
        }
    }
}

@Composable
private fun ErrorState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.general_error),
            style = AppTheme.typography.bodyMedium
        )
    }
}