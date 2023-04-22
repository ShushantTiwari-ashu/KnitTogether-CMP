package dev.shushant.resource.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigator: Navigator = rememberNavigator()
): AppState {
    return remember(coroutineScope, navigator) {
        AppState(coroutineScope, navigator)
    }
}

@Stable
class AppState(
    val coroutineScope: CoroutineScope,
    val navigator: Navigator,
) {

    val currentDestination: String
        @Composable get() = navigator.currentEntry.collectAsState(null).value?.route?.route ?: ""


    fun navigate(route: String, popUpTo: Boolean = false) {
        val navOptions = if (popUpTo) NavOptions(
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo = PopUpTo(route),
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item

        ) else null
        navigator.navigate(route, navOptions)
    }

    /*@Composable
    fun showNavIcon(): Boolean {
        return currentDestination == Screen.WebView.route
    }*/
}
