package com.example.futurenote.ui

import android.widget.EditText
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.futurenote.nav.BottomNavBar

@Composable

fun AddNoteScreen(navController: NavController) {
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination?.route
    var text by remember {mutableStateOf("")}
    val focusManager = LocalFocusManager.current

    Scaffold (
        bottomBar = {
            // I don't think we need this if statement, but I'm keeping it for now
            if (currentDestination == "addNote") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigate("home") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                    OutlinedButton(
                        onClick = {

                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                // Clears the focus whenever you're clicking outside of the TextFields.
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while(true) {
                            val event = awaitPointerEvent()
                            if(event.changes.any {it.pressed}) {
                                focusManager.clearFocus()
                            }
                        }
                    }
                }
        ) {
            Text("Add Future Note")
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                label = {Text("Hey Future firstname, ")},
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = Int.MAX_VALUE,
                minLines = 5,
                singleLine = false

            )

        }
    }
}