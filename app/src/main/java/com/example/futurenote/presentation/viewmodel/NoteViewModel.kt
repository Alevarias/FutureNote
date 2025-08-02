package com.example.futurenote.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurenote.data.local.Note
import com.example.futurenote.data.repository.NoteRepository
import com.example.futurenote.presentation.states.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> = _state

    init {
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch {
            repository.notes
                .onStart { _state.update { it.copy(isLoading = true) } }
                .catch { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
                .collect { noteList ->
                    _state.update { it.copy(notes = noteList, isLoading = false) }
                }
        }
    }
    
    fun addNote(noteTitle: String, noteContent: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(Note(noteTitle = noteTitle, noteContent = noteContent, timestamp = System.currentTimeMillis()))
        }
    }
    
    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    fun selectNote(note: Note) {
        _state.update { it.copy(selectedNote = note) }
    }
    
    fun toggleDialog(note: Note) {
        _state.update { it.copy(isDialogVisible = !it.isDialogVisible) }
    }
}