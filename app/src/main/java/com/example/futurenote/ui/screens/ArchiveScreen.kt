package com.example.futurenote.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ArchiveScreen(navController: NavController) {
    var text by remember {mutableStateOf("")}
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Enter something") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}