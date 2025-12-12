package ru.heavycoffee.state.reducer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

class StateReducer<State, Effect>(
    private val logTag: String,
    initialState: State,
    coroutineScope: CoroutineScope
) {
    private val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<State> get() = mutableState

    private val mutableEffect: MutableSharedFlow<Effect> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val effect: SharedFlow<Effect> get() = mutableEffect

    private val mutableEventFlow = MutableSharedFlow<State.() -> State>(extraBufferCapacity = Int.MAX_VALUE)

    init {
        coroutineScope.launch {
            mutableEventFlow.scan(
                initial = initialState,
                operation = { state, event ->
                    event.invoke(state)
                }
            ).collect { state ->
                mutableState.value = state
            }
        }
    }

    /**
     * To send events with a state change
     */
    fun reduce(event: State.() -> State) {
        mutableEventFlow.tryEmit(event)
    }

    /**
     * To send an event without changing the state
     */
    fun intent(event: (State) -> Unit) {
        reduce {
            event.invoke(this)
            this
        }
    }

    fun postEffect(effect: Effect) {
        mutableEffect.tryEmit(effect)
    }
}