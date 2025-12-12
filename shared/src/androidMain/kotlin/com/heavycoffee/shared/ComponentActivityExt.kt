package com.heavycoffee.shared

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import com.heavycoffee.navigation.RootComponent
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

fun ComponentActivity.initApp() {
    val rootComponent: RootComponent by retainedComponent(key = "RetainedComponent") {
        inject<RootComponent> { parametersOf(it) }
    }
    setContent {
        RandomUserApp(rootComponent)
    }
}