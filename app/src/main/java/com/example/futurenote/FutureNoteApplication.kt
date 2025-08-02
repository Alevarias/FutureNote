package com.example.futurenote

import android.app.Application
import com.example.futurenote.data.local.NoteDatabase
import com.example.futurenote.data.repository.NoteRepository

class FutureNoteApplication : Application() {
    private val database by lazy { NoteDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}
