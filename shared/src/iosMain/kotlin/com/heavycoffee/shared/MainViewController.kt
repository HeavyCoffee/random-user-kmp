package com.heavycoffee.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.heavycoffee.navigation.RootComponent
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

fun MainViewController() = ComposeUIViewController {
    val root = koinInject<RootComponent> {
        parametersOf(DefaultComponentContext(LifecycleRegistry())) }
    RandomUserApp(root)
}