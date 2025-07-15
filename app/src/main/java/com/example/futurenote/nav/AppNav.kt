package com.example.futurenote.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.futurenote.ui.AddNoteScreen
import com.example.futurenote.ui.ArchiveScreen
import com.example.futurenote.ui.HomeScreen
import com.example.futurenote.ui.SettingsScreen
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.getValue


@Preview
@Composable
fun AppNav() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavScreens = listOf("home", "archive", "settings")

    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentDestination in bottomNavScreens) {
                BottomNavBar(navController)
            }
        },
        floatingActionButton = {
            if (currentRoute != "addNote" && currentRoute != "settings") {
                FloatingActionButton(onClick = {
                    navController.navigate("addNote") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        }
    ) {
            innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("archive") {
                ArchiveScreen(navController)
            }
            composable("settings") {
                SettingsScreen(navController)
            }
            composable("addNote") {
                AddNoteScreen(navController)
            }
        }
    }
}