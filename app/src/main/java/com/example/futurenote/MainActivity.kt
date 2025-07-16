package com.example.futurenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.futurenote.ui.navigation.AppNav
import com.example.futurenote.ui.theme.FutureNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FutureNoteTheme {
                AppNav()
            }
        }
    }
}