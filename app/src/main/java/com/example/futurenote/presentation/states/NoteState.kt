package com.example.futurenote.presentation.states

import com.example.futurenote.data.local.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedNote: Note? = null,

    val isDialogVisible: Boolean = false
)
