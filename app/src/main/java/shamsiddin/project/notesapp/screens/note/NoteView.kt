package shamsiddin.project.notesapp.screens.note

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.notesapp.database.AppDataBase
import shamsiddin.project.notesapp.database.entity.Note
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(noteViewModel: NoteViewModel){
    val context = LocalContext.current
    val appDataBase = AppDataBase.getInstance(context)

    var state  = noteViewModel.state.observeAsState().value!!
    val title = noteViewModel.title.observeAsState().value!!
    val text = noteViewModel.text.observeAsState().value!!

//    val formatter = SimpleDateFormat("dd-Month-yyyy HH-mm")
//    val currentDate by remember { mutableStateOf(formatter.format(Calendar.getInstance().time)) }



    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Row(modifier = Modifier.align(Alignment.CenterStart), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { noteViewModel.onBackPressed() }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "", modifier = Modifier.size(30.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Add Note",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            if (state){
                IconButton(onClick = {
                    noteViewModel.doneButton()
                }, modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "", modifier = Modifier.size(30.dp))
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)) {
            TextField(
                value = title,
                onValueChange = {noteViewModel.updateTitle(it)},
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent),
                singleLine = false,
                label = {if (title.isEmpty()) Text(text = "Title", color = Color.LightGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)},
                textStyle = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "currentDate", fontSize = 15.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = text,
                onValueChange = { noteViewModel.updateText(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { state = it.isFocused },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent),
                singleLine = false,
                label = {if (text.isEmpty()) Text(text = "Start typing", color = Color.LightGray, fontSize = 17.sp)},
                textStyle = TextStyle(color = Color.Black, fontSize = 17.sp)
            )
        }
    }
}

@Composable
@Preview
fun NotePreview(){
    val nvm = NoteViewModel(rememberNavController(), LocalContext.current, -1)
    NoteView(noteViewModel = nvm)
}