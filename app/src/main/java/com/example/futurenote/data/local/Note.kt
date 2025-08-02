package com.example.futurenote.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteTitle: String,
    val noteContent: String,
    val timestamp: Long = System.currentTimeMillis()

)
