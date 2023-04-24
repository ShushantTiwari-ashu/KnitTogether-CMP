package dev.shushant.knit_together.ui.signin


import dev.shushant.resource.common.AuthValidator
import dev.shushant.resource.common.ViewModel
import dev.shushant.resource.common.ViewModelCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel(
    viewModelScope: CoroutineScope = ViewModelCoroutineScope()
) : ViewModel<LoginState>(viewModelScope, LoginState()) {

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
            delay(2_000L)
            setState { state ->
                state.copy(
                    isLoading = false, isLoggedIn = true, error = null
                )
            }

        }
    }

}