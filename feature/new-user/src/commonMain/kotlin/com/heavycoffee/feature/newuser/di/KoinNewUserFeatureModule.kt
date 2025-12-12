package com.heavycoffee.feature.newuser.di

import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.feature.newuser.component.NewUserComponent
import com.heavycoffee.feature.newuser.component.NewUserComponentImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val koinNewUserFeatureModule = module {
    factory<NewUserComponent> {
        (
            componentContext: ComponentContext,
            onBack: () -> Unit,
            onOpenUserList: () -> Unit
        ) ->
        NewUserComponentImpl(
            componentContext = componentContext,
            generateUserUseCase = get(),
            isUserListNotEmptyUseCase = get(),
            getGenderListUseCase = get(),
            getNationalityListUseCase = get(),
            saveUserUseCase = get(),
            onBack = onBack,
            onOpenUserList = onOpenUserList
        )
    }
}

fun KoinComponent.getNewUserComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit,
    onOpenUserList: () -> Unit
): NewUserComponent = get {
    parametersOf(componentContext, onBack, onOpenUserList)
}