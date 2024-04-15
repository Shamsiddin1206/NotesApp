package shamsiddin.project.notesapp.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category"]
        )
    ])
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val text: String,
    val timeDate: String,
    val category: Int? = null,
    val title: String,
)
