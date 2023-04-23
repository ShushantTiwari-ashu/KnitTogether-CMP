package dev.shushant.knit_together.ui.signin

import dev.shushant.network.expectations.DispatcherMain
import dev.shushant.resource.common.AuthValidator
import dev.shushant.resource.common.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SignInViewModel(
    viewModelScope: CoroutineScope = CoroutineScope(DispatcherMain + SupervisorJob())
) : ViewModel<LoginState>(viewModelScope, LoginState()) {

    fun setEmail(email: String) {
        setState { state -> state.copy(email = email) }
    }

    fun setPassword(password: String) {
        setState { state -> state.copy(password = password) }
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

}