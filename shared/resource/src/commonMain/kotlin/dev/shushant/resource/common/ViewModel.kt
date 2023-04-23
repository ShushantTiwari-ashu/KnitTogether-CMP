package dev.shushant.resource.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

/**
 * The base state-holder model for the UI for performing UI-business operations
 *
 * @property viewModelScope The coroutine scope which will be bound with the UI lifecycle
 */
abstract class ViewModel<STATE>(protected val viewModelScope: CoroutineScope,initialState: STATE) {
    /**
     * Mutable State of this ViewModel
     */
    private val _state = MutableStateFlow(initialState)

    /**
     * State to be exposed to the UI layer
     */
    val state: StateFlow<STATE> = _state.asStateFlow()

    /**
     * Retrieves the current UI state
     */
    val currentState: STATE get() = state.value

    /**
     * Updates the state of this ViewModel and returns the new state
     *
     * @param update Lambda callback with old state to calculate a new state
     * @return The updated state
     */
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)

    /**
     * Invoked when the [ViewModel] is no longer needed. Do clean-up stuff here.
     */
    open fun onCleared() {}
}

/**
 * The factory for creating instance of [ViewModel] in the composition.
 *
 * THIS APPROACH IS NOT LOOKING MUCH GOOD. SO NEED TO FIND SOMETHING WHICH MIGHT BE RELIABLE.
 * TILL THE TIME, LET THIS BE THE EXPERIMENTAL IMPLEMENTATION.
 * TODO: Use something better approach
 *
 * @param provideViewModel Lambda factory for creating ViewModel
 */
@Composable
fun <VM : ViewModel<STATE>, STATE> viewModelFactory(provideViewModel: (CoroutineScope) -> VM): VM {
    val viewModelScope = remember { ViewModelCoroutineScope() }
    val viewModel = remember { provideViewModel(viewModelScope) }

    DisposableEffect(true) {
        onDispose {
            viewModelScope.cancel()
            viewModel.onCleared()
        }
    }

    return viewModel
}