package com.example.futurenote.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.futurenote.presentation.screens.AddNoteScreen
import com.example.futurenote.presentation.screens.ArchiveScreen
import com.example.futurenote.presentation.screens.HomeScreen
import com.example.futurenote.presentation.screens.SettingsScreen
import com.example.futurenote.presentation.viewmodel.NoteViewModel
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.getValue
import com.example.futurenote.R


@Composable
fun AppNav(noteViewModel: NoteViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavScreens = listOf("home", "archive", "settings")

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavScreens) {
                BottomNavBar(navController)
            }
        },
        floatingActionButton = {
            if (currentRoute != "addNote" && currentRoute != "settings") {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("addNote")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, 
                        contentDescription = stringResource(R.string.add_note_fab)
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController, noteViewModel)
            }
            composable("archive") {
                ArchiveScreen(navController, noteViewModel)
            }
            composable("settings") {
                SettingsScreen(navController)
            }
            composable("addNote") {
                AddNoteScreen(navController, noteViewModel)
            }
        }
    }
}