package shamsiddin.project.notesapp.screens.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.navigation.NavController
import shamsiddin.project.notesapp.database.AppDataBase
import shamsiddin.project.notesapp.database.dao.NoteDao
import shamsiddin.project.notesapp.database.entity.Note
import shamsiddin.project.notesapp.navigation.ScreenType

class HomeViewModel(private val navController: NavController, context: Context){
    private val appDataBase = AppDataBase.getInstance(context)
    private val dao = appDataBase.getDao()

    private val _allNotes = MediatorLiveData(dao.getAllNotes().reversed())
    val allNotes: LiveData<List<Note>> = _allNotes

    fun addNewNoteButton(){
        navController.navigate("note/-1")
    }

    fun updateNoteButton(id: Int){
        navController.navigate("note/${id}")
    }

    fun deleteButton(note: Note) {
        dao.deleteNote(note)
        _allNotes.value = dao.getAllNotes().reversed()
    }
}