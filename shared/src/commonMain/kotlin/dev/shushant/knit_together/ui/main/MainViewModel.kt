package dev.shushant.knit_together.ui.main

import dev.shushant.knit_together.ui.createpost.PostData
import dev.shushant.persistence.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class MainViewModel : ViewModel(), KoinComponent {

    private val appSettings: AppSettings by inject()
    private val _state = MutableStateFlow(MainState())

    /**
     * State to be exposed to the UI layer
     */
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        checkUserDataAvailable()
    }

    private fun checkUserDataAvailable() {
        viewModelScope.launch {
            appSettings.loginData.collectLatest {
                it?.let {
                    setState { state -> state.copy(isLoggedIn = true, showBottomBar = true) }
                }
            }
        }
    }

    fun updatePostData(postData: PostData?) {
        postData?.let {
            setState { state -> state.copy(postData = postData) }
        }
    }

    private fun setState(update: (old: MainState) -> MainState): MainState =
        _state.updateAndGet(update)

}


internal data class MainState(
    val showBottomBar: Boolean = false,
    val isTopLevel: Boolean = false,
    val currentDestination: String = "",
    val isLoggedIn: Boolean = true,
    val postData: PostData? = null
)