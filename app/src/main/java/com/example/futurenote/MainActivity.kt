package com.example.futurenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futurenote.presentation.navigation.AppNav
import com.example.futurenote.presentation.theme.FutureNoteTheme
import com.example.futurenote.presentation.viewmodel.NoteViewModel
import com.example.futurenote.presentation.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FutureNoteTheme {
                val application = application as FutureNoteApplication
                val viewModelFactory = NoteViewModelFactory(application.repository)
                val noteViewModel: NoteViewModel = viewModel(factory = viewModelFactory)
                
                AppNav(noteViewModel = noteViewModel)
            }
        }
    }
}