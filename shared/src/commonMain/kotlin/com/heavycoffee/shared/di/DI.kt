package com.heavycoffee.shared.di

import com.heavycoffee.navigation.di.koinNavigationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object DI {
    fun start(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(coreModules() + featureModules() + koinNavigationModule)
    }

    fun start() = start {}
}