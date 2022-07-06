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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.screens.NoteViewModel
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

@Composable
// here first we are instantiating note view model object
// so we can work with it
fun NotesApp(noteViewModel: NoteViewModel = viewModel()){

    // first we gonna get the list to populate
    // our composable function
    val notesList = noteViewModel.getNoteList()

    // putting all the necessary functions inside
    // our note screen
    NotesScreen(noteList = notesList,
        removeNote = {note ->

            noteViewModel.removeNote(note)
        },
        addNote = {note ->

            noteViewModel.addNote(note)
        }
    )
}

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetNoteAppTheme {

    }
}