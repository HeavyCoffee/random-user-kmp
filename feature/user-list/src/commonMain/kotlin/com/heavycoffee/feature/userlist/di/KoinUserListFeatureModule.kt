package com.heavycoffee.feature.userlist.di

import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.feature.userlist.component.UserListComponent
import com.heavycoffee.feature.userlist.component.UserListComponentImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val koinUserListFeatureModule = module {
    factory<UserListComponent> {
        (
            componentContext: ComponentContext,
            onOpenUserInfo: (id: Long) -> Unit,
            onAddNewUser: () -> Unit
        ) ->
        UserListComponentImpl(
            componentContext = componentContext,
            observeUserListUseCase = get(),
            onOpenUserInfo = onOpenUserInfo,
            onAddNewUser = onAddNewUser
        )
    }
}

fun KoinComponent.getUserListComponent(
    componentContext: ComponentContext,
    onOpenUserInfo: (id: Long) -> Unit,
    onAddNewUser: () -> Unit
): UserListComponent = get {
    parametersOf(componentContext, onOpenUserInfo, onAddNewUser)
}