package dev.shushant.persistence

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import dev.shushant.network.extensions.toJsonString
import dev.shushant.network.extensions.toObject
import dev.shushant.network.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalSettingsApi::class)
class AppSettings(private val settings: ObservableSettings) {

    val loginData: Flow<LoginResponse?> =
        settings.getStringOrNullFlow(USERDATA).map {
            it?.let {
                getUserData(it)
            }
        }

    private fun getUserData(settingsString: String) =
        settingsString.toObject<LoginResponse>()

    fun updateLoginData(data: LoginResponse) {
        settings.putString(USERDATA, data.toJsonString(LoginResponse.serializer()))
    }

    companion object {
        const val USERDATA = "user_data"
    }
}
