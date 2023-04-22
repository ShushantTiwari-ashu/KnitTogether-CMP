package dev.shushant.persistence

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings

class AppSettings(private val settings: ObservableSettings) {

    companion object {
        const val LOCATION_DATA = "location_data"
        const val WEATHER_DATA = "weather_data"
        const val SEARCHED_DATA = "searched_data"
    }
}
