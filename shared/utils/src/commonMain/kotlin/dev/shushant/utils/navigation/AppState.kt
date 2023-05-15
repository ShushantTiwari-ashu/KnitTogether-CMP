package dev.shushant.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun rememberAppState(
    navigator: Navigator = rememberNavigator()
): AppState {
    return remember(navigator) {
        AppState(navigator)
    }
}

@Stable
class AppState(
    val navigator: Navigator,
) {

    private val currentDestination: String
        @Composable get() = navigator.currentEntry.collectAsState(null).value?.route?.route ?: ""

    var onPostClick: () -> Unit = {}


    fun navigate(route: String, popUpTo: Boolean = false) {
        val navOptions = if (popUpTo) NavOptions(
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo = PopUpTo(route),
            // Avoid multiple copies of the same destination when
            // reelecting the same item
            launchSingleTop = true
            // Restore state when reelecting a previously selected item

        ) else null
        navigator.navigate(route, navOptions)
    }

    private val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination) {
            TopLevelDestination.HOME.name -> TopLevelDestination.HOME
            TopLevelDestination.SEARCH.name -> TopLevelDestination.SEARCH
            TopLevelDestination.CREATE.name -> TopLevelDestination.CREATE
            TopLevelDestination.NOTIFICATION.name -> TopLevelDestination.NOTIFICATION
            TopLevelDestination.PROFILE.name -> TopLevelDestination.PROFILE
            else -> null
        }

    fun createPost() = onPostClick.invoke()




    @Composable
    fun showNavIcon(): Boolean {
        return currentTopLevelDestination != null
    }

    @Composable
    fun showPost() = currentDestination == TopLevelDestination.CREATE.name

    @Composable
    fun showTopAppBar() =
        currentDestination == TopLevelDestination.HOME.name || currentDestination == TopLevelDestination.PROFILE.name || currentDestination == TopLevelDestination.NOTIFICATION.name

    @Composable
    fun isTopLevelDestinationInHierarchy(destination: TopLevelDestination): Boolean {
        return destination.name == currentDestination
    }
}


enum class TopLevelDestination(
    val selectedIcon: String,
    val unselectedIcon: String,
) {
    HOME(
        selectedIcon = "home_selected.xml", unselectedIcon = "home_unselected.xml"
    ),
    SEARCH(
        selectedIcon = "search_selected.xml", unselectedIcon = "search_unselected.xml"
    ),
    CREATE(
        selectedIcon = "create_post_selected.xml", unselectedIcon = "create_post_unselected.xml"
    ),
    NOTIFICATION(
        selectedIcon = "notification_selected.xml", unselectedIcon = "notification_unselected.xml"
    ),
    PROFILE(
        selectedIcon = "profile_selected.xml", unselectedIcon = "profile_selected.xml"
    ),
}