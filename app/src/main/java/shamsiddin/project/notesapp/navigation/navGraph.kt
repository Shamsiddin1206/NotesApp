package shamsiddin.project.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import shamsiddin.project.notesapp.screens.home.HomeView
import shamsiddin.project.notesapp.screens.home.HomeViewModel
import shamsiddin.project.notesapp.screens.note.NoteView
import shamsiddin.project.notesapp.screens.note.NoteViewModel

@Composable
fun SetNavGraph(navController: NavHostController){
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = ScreenType.Home.route) {
        composable(ScreenType.Home.route){
            val hvm = HomeViewModel(navController, context)
            HomeView(homeViewModel = hvm)
        }
        composable(ScreenType.Note.route, arguments = listOf(navArgument("id"){
            type = NavType.IntType
        })){it ->
            val id = it.arguments?.getInt("id")!!
            val nvm = NoteViewModel(navController, context, id)
            NoteView(nvm)
        }
    }
}