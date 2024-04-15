package shamsiddin.project.notesapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import shamsiddin.project.notesapp.database.entity.Category
import shamsiddin.project.notesapp.database.entity.Note

@Dao
interface NoteDao {
    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Insert
    fun addCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

    @Query("select * from notes")
    fun getAllNotes(): List<Note>

    @Query("select * from category")
    fun getAllCategories(): List<Category>

    @Query("select * from notes where category = :category")
    fun getNotesByCategory(category: Int): List<Note>

    @Query("select * from notes where id = :id")
    fun getNoteById(id: Int): Note

    @Query("select * from notes where title = :title")
    fun getNoteByTitle(title: String): Note?
}