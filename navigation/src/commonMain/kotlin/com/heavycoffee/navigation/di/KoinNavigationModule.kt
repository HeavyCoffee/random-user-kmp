package com.heavycoffee.navigation.di

import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.navigation.RootComponent
import com.heavycoffee.navigation.RootComponentImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val koinNavigationModule = module {
    factory<RootComponent> { (componentContext: ComponentContext) ->
        RootComponentImpl(
            componentContext = componentContext,
            isUserListNotEmptyUseCase = get()
        )
    }
}

fun KoinComponent.injectRootComponent(
    componentContext: ComponentContext
): RootComponent = get { parametersOf(componentContext) }