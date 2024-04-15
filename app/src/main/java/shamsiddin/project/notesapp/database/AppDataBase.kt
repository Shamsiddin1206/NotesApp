package shamsiddin.project.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import shamsiddin.project.notesapp.database.dao.NoteDao
import shamsiddin.project.notesapp.database.entity.Category
import shamsiddin.project.notesapp.database.entity.Note

@Database(entities = [Category::class, Note::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getDao(): NoteDao

    companion object{
        var instance: AppDataBase? = null
        fun getInstance(context: Context):AppDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "app_db").allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}