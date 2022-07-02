package com.example.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.screens.NotesScreen
import com.example.jetnoteapp.ui.theme.JetNoteAppTheme

/* at the start of the app we gonna add
* all the appropriate packages to segregate our
* data better */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {

                val notes = remember{
                    mutableStateListOf<Note>()
                }

                NotesScreen(notes,
                    removeNote = {note ->
                    notes.remove(note) },
                    addNote = {note ->
                        notes.add(note)}
                )
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetNoteAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            
            content()
            
        }
    }
    
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetNoteAppTheme {
        MyApp {

        }
    }
}