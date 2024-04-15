package shamsiddin.project.notesapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.notesapp.database.AppDataBase
import shamsiddin.project.notesapp.database.entity.Note
import shamsiddin.project.notesapp.ui.theme.AddButtonColor
import shamsiddin.project.notesapp.ui.theme.Grey_Primary

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeView(homeViewModel: HomeViewModel){
//    val appDataBase = AppDataBase.getInstance(LocalContext.current)
//    val allNotes by remember { mutableStateOf(appDataBase.getDao().getAllNotes()) }
//    val categoryList by remember { mutableStateOf(appDataBase.getDao().getAllCategories().toMutableList()) }
//    val category = Category(-1, "All")
//    val selectedCategory by remember { mutableStateOf(category) }
//    val filtredNotes by remember { mutableStateOf(appDataBase.getDao().getNotesByCategory(selectedCategory))  }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Grey_Primary)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Notes",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 60.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
//            LazyRow(modifier = Modifier
//                .fillMaxWidth()) {
//                categoryList.add(categoryList.lastIndex, Category(-2, "+"))
//                categoryList.add(0, Category(-1, "All"))
//                items(categoryList){
//                    CategoryItem(s = it.name)
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(10.dp))
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp), shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .background(Grey_Primary)) {
                    items(homeViewModel.allNotes.value!!){
                        NoteItem(note = it, (it==homeViewModel.allNotes.value!!.last()), homeViewModel)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        FloatingActionButton(onClick = { homeViewModel.addNewNoteButton() }, containerColor = AddButtonColor, modifier = Modifier
            .align(
                Alignment.BottomEnd
            )
            .padding(end = 20.dp, bottom = 20.dp), shape = RoundedCornerShape(50)
        ) {
            Text(text = "+", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun CategoryItem(s: String){
    Card(modifier = Modifier.padding(start = 5.dp, end = 5.dp), elevation = CardDefaults.cardElevation(5.dp), colors = CardDefaults.cardColors(
        Color.White)) {
        Text(text = s, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp))
    }
}

@Composable
fun NoteItem(note: Note, last: Boolean, homeViewModel: HomeViewModel){
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { homeViewModel.updateNoteButton(note.id) }
        .padding(bottom = if (!last) 10.dp else 0.dp, start = 15.dp, end = 15.dp), elevation = CardDefaults.cardElevation(2.dp), shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(
        Color.White)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = note.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black, maxLines = 1)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = note.text, fontSize = 15.sp, maxLines = 1, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = note.timeDate, fontSize = 12.sp, maxLines = 1, color = Color.Gray)
            }
            IconButton(onClick = { homeViewModel.deleteButton(note) }, modifier = Modifier.align(
                Alignment.CenterEnd).padding(end = 10.dp)) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "", modifier = Modifier.size(height = 40.dp, width = 30.dp))
            }
        }
    }
}

@Composable
@Preview
fun HomePreview(){
    val homeViewModel = HomeViewModel(rememberNavController(), LocalContext.current)
    HomeView(homeViewModel = homeViewModel)
}

