package dev.shushant.resource.navigation

sealed class Screens {
    object Splash : Screens()
    object Home : Screens()
}

data class Navigator(
    val push: (Screens) -> Unit,
    val pop: () -> Unit
)

