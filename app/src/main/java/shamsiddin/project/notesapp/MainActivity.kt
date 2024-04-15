package shamsiddin.project.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.notesapp.database.AppDataBase
import shamsiddin.project.notesapp.database.entity.Note
import shamsiddin.project.notesapp.navigation.SetNavGraph
import shamsiddin.project.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetNavGraph(navController = rememberNavController())
                    val appDataBase = AppDataBase.getInstance(this)
                    val note1 = Note(text = "Java is a programming language and computing platform first released by Sun Microsystems in 1995. It has evolved from humble beginnings to power a large share of today’s digital world, by providing the reliable platform upon which many services and applications are built. New, innovative products and digital services designed for the future continue to rely on Java, as well.\n" +
                            "\n" +
                            "While most modern Java applications combine the Java runtime and application together, there are still many applications and even some websites that will not function unless you have a desktop Java installed. Java.com, this website, is intended for consumers who may still require Java for their desktop applications – specifically applications targeting Java 8. Developers as well as users that would like to learn Java programming should visit the dev.java website instead and business users should visit oracle.com/java for more information.", title = "Java", timeDate = "April 14, 2024")
//                    appDataBase.getDao().addNote(note1)
//                    appDataBase.getDao().addNote(note1)
//                    appDataBase.getDao().addNote(note1)
//                    appDataBase.getDao().addNote(note1)
//                    appDataBase.getDao().addNote(note1)
//                    appDataBase.getDao().addNote(note1)
                }
            }
        }
    }
}
