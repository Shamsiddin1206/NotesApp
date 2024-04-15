package shamsiddin.project.notesapp.navigation

sealed class ScreenType(val route: String) {
    data object Home: ScreenType("home")
    data object Note: ScreenType("note/{id}")
}