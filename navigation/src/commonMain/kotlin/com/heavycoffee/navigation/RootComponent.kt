package com.heavycoffee.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.heavycoffee.core.database.domain.usecase.IsUserListNotEmptyUseCase
import com.heavycoffee.core.feature.decompose.ext.componentScope
import com.heavycoffee.feature.newuser.component.NewUserComponent
import com.heavycoffee.feature.newuser.di.getNewUserComponent
import com.heavycoffee.feature.newuser.ui.NewUserScreen
import com.heavycoffee.feature.userinfo.component.UserInfoComponent
import com.heavycoffee.feature.userinfo.di.getUserInfoComponent
import com.heavycoffee.feature.userinfo.ui.UserInfoScreen
import com.heavycoffee.feature.userlist.component.UserListComponent
import com.heavycoffee.feature.userlist.di.getUserListComponent
import com.heavycoffee.feature.userlist.ui.UserListScreen
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

interface RootComponent : KoinComponent {
    val stack: Value<ChildStack<*, ScreenComponent>>
}

internal class RootComponentImpl(
    componentContext: ComponentContext,
    private val isUserListNotEmptyUseCase: IsUserListNotEmptyUseCase
) : RootComponent, ComponentContext by componentContext {

    init {
        componentScope.launch {
            if (isUserListNotEmptyUseCase().getOrDefault(false)) {
                navigation.replaceCurrent(ScreenConfig.UserList)
            } else {
                navigation.replaceCurrent(ScreenConfig.NewUser)
            }
        }
    }

    private val navigation = StackNavigation<ScreenConfig>()

    override val stack: Value<ChildStack<*, ScreenComponent>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        initialConfiguration = ScreenConfig.Launch,
        handleBackButton = false,
        childFactory = ::screenComponentFactory
    )

    private fun screenComponentFactory(
        config: ScreenConfig,
        componentContext: ComponentContext
    ): ScreenComponent = when (config) {
        is ScreenConfig.Launch -> ScreenComponent.Launch
        is ScreenConfig.NewUser -> ScreenComponent.NewUser(
            component = getNewUserComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() },
                onOpenUserList = { navigation.replaceCurrent(ScreenConfig.UserList) }
            )
        )
        is ScreenConfig.UserList -> ScreenComponent.UserList(
            component = getUserListComponent(
                componentContext = componentContext,
                onOpenUserInfo = { id -> navigation.pushNew(ScreenConfig.UserInfo(id)) },
                onAddNewUser = { navigation.pushNew(ScreenConfig.NewUser) }
            )
        )
        is ScreenConfig.UserInfo -> ScreenComponent.UserInfo(
            component = getUserInfoComponent(
                id = config.id,
                componentContext = componentContext,
                onBack = { navigation.pop() }
            )
        )
    }
}

@Serializable
sealed interface ScreenConfig {
    @Serializable
    data object Launch : ScreenConfig
    @Serializable
    data object NewUser : ScreenConfig
    @Serializable
    data object UserList : ScreenConfig
    @Serializable
    data class UserInfo(val id: Long) : ScreenConfig
}

sealed interface ScreenComponent {
    @Composable
    abstract fun Content()

    data object Launch : ScreenComponent {
        @Composable
        override fun Content() = Unit
    }

    data class NewUser(val component: NewUserComponent) : ScreenComponent {
        @Composable
        override fun Content() = NewUserScreen(component)
    }
    data class UserInfo(val component: UserInfoComponent) : ScreenComponent {
        @Composable
        override fun Content() = UserInfoScreen(component)
    }
    data class UserList(val component: UserListComponent) : ScreenComponent {
        @Composable
        override fun Content() = UserListScreen(component)
    }
}