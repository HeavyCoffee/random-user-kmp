package com.heavycoffee.core.feature.decompose.ext

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import ru.heavycoffee.state.reducer.StateReducer

val ComponentContext.componentScope: CoroutineScope
    get() {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

        if (lifecycle.state != Lifecycle.State.DESTROYED) {
            lifecycle.doOnDestroy {
                scope.cancel()
            }
        } else {
            scope.cancel()
        }
        return scope
    }

fun <ViewState, ViewEffect> ComponentContext.state(
    initialState: ViewState
) = StateReducer<ViewState, ViewEffect>(
    logTag = this::class.simpleName.toString(),
    initialState = initialState,
    coroutineScope = componentScope
)