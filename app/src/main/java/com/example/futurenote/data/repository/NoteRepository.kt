package com.example.futurenote.data.repository
import com.example.futurenote.data.local.Note
import com.example.futurenote.data.local.NoteDao
import kotlinx.coroutines.flow.Flow

// Really isn't needed right now since I'm only grabbing from the local database, but good to learn
class NoteRepository (private val dao: NoteDao) {
    val notes: Flow<List<Note>> = dao.getAllNotes()

    suspend fun addNote(note: Note) = dao.upsert(note)
    suspend fun deleteNote(note: Note) = dao.delete(note)
}