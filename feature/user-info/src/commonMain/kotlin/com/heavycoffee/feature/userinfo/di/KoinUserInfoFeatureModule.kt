package com.heavycoffee.feature.userinfo.di

import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.feature.userinfo.component.UserInfoComponent
import com.heavycoffee.feature.userinfo.component.UserInfoComponentImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val koinUserInfoFeatureModule = module {
    factory<UserInfoComponent> { (id: Long, componentContext: ComponentContext, onBack: () -> Unit) ->
        UserInfoComponentImpl(
            id = id,
            componentContext = componentContext,
            getUserByIdUseCase = get(),
            onBack = onBack
        )
    }
}

fun KoinComponent.getUserInfoComponent(
    id: Long,
    componentContext: ComponentContext,
    onBack: () -> Unit
): UserInfoComponent = get {
    parametersOf(id, componentContext, onBack)
}