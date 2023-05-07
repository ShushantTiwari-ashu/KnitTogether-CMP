package dev.shushant.knit_together.ui.signin

import dev.shushant.network.api.KnitTogetherApi
import dev.shushant.network.functional.Either
import dev.shushant.network.model.SignupRequest
import dev.shushant.persistence.AppSettings
import dev.shushant.resource.common.AuthValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInViewModel(
) : ViewModel(), KoinComponent {

    private val knitTogetherApi: KnitTogetherApi by inject()
    private val appSettings: AppSettings by inject()
    private val _state = MutableStateFlow(LoginState())

    private val currentState: LoginState get() = state.value

    /**
     * State to be exposed to the UI layer
     */
    val state: StateFlow<LoginState> = _state.asStateFlow()
    fun setEmail(email: String) {
        if (email.isNotBlank() && AuthValidator.isValidEmail(email)) {
            setState { state -> state.copy(email = email, isValidEmail = true) }
        } else {
            setState { state -> state.copy(email = email, isValidEmail = false) }
        }
    }

    fun setPassword(password: String) {
        if (password.isNotBlank() && AuthValidator.isValidPassword(password)) {
            setState { state -> state.copy(password = password, isValidPassword = true) }
        } else {
            setState { state -> state.copy(password = password, isValidPassword = false) }
        }
    }

    fun clearError() = setState { state -> state.copy(error = null) }

    private fun validateCredentials(): Boolean {
        val isValidEmail = AuthValidator.isValidEmail(currentState.email)
        val isValidPassword = AuthValidator.isValidPassword(currentState.password)

        setState { state ->
            state.copy(
                isValidEmail = isValidEmail, isValidPassword = isValidPassword
            )
        }

        return isValidEmail && isValidPassword
    }

    fun login() {
        if (!validateCredentials()) return

        viewModelScope.launch {
            setState { state -> state.copy(isLoading = true) }
            when (val result =
                knitTogetherApi.signIn(
                    SignupRequest(
                        email = currentState.email,
                        password = currentState.password
                    )
                )) {
                is Either.Failure -> {
                    setState { state ->
                        state.copy(
                            isLoading = false, isLoggedIn = false, error = result.a.message
                        )
                    }
                }

                is Either.Success -> {
                    appSettings.updateLoginData(result.b)
                    setState { state ->
                        state.copy(
                            isLoading = false, isLoggedIn = true, error = null
                        )
                    }
                }
            }

        }
    }

    private fun setState(update: (old: LoginState) -> LoginState): LoginState =
        _state.updateAndGet(update)

}