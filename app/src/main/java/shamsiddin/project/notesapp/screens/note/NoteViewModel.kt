package shamsiddin.project.notesapp.screens.note

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import shamsiddin.project.notesapp.database.AppDataBase
import shamsiddin.project.notesapp.database.entity.Note
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NoteViewModel(private val navController: NavController, val context: Context, val id: Int) {
    private val appDataBase = AppDataBase.getInstance(context)
    private val dao = appDataBase.getDao()

    private val _state = MutableLiveData(false)
    val state: LiveData<Boolean> = _state

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    private val _date = MutableLiveData("")
    val date: LiveData<String> = _text

    var note = Note(text = "", title = "", timeDate = "")


    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


//    private val _currentTime = MutableLiveData(formatter.format(Calendar.getInstance().time))
//    val currentTime: LiveData<String> = _currentTime

    init {
        if (id > -1) {
            val note1 = dao.getNoteById(id)
            _title.value = note1.title
            _text.value = note1.text
            _date.value = note1.timeDate
            note = note1
        }
    }

    private fun confirmNewNoteButton() {
        navController.navigate("home")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun doneButton() {
        var s = false
        val note1 = Note(text = text.value!!, timeDate = formatter.format(LocalDate.now()), title = title.value!!)
        if (note1.text.isNotEmpty()) {
            if (note1.title.isNotEmpty() && checkPlagiarism(note1.title)) {
                if (note.id==-1){
                    dao.addNote(note1)
                }else{
                    note1.id = note.id
                    dao.updateNote(note1)
                }
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                s = true
            } else {
                Toast.makeText(context, "Enter Title or invalid title", Toast.LENGTH_SHORT).show()
                s = false
            }
        }
        if (s) {
            confirmNewNoteButton()
        }
    }

    private fun checkPlagiarism(s: String): Boolean {
        return dao.getNoteByTitle(s) == null
    }

    fun onBackPressed() {
        navController.navigate("home")
    }

    fun updateText(it: String) {
        _text.value = it
        checkState()
    }

    fun updateTitle(it: String) {
        _title.value = it
        checkState()
    }

    private fun checkState() {
        if (id != -1) {
            _state.value = note.title != title.value || note.text != text.value
        } else {
            _state.value = text.value!!.isNotEmpty() || title.value!!.isNotEmpty()
        }
    }

}