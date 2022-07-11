package com.example.jetnoteapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

/* at the start of the app we gonna add
* all the appropriate packages to segregate our
* data better */

// defining the entry point for hilt
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {

                // this how you instantiate your view model class
                // (need to learn more about by delegate)
                // also it is very important to instantiate your
                // view model class out of the composable function
                // so the data can survive the recomposition
                // after that you can pass the data as parameter
                // inside the composable function

                // another way to instantiate view model
                //val noteViewModel = viewModel<NoteViewModel>() // also works

                val noteViewModel: NoteViewModel by viewModels()

                NotesApp(noteViewModel)
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
fun NotesApp(noteViewModel: NoteViewModel){

    // first we gonna get the list to populate
    // our composable function
    val notesList = noteViewModel
        .noteList.collectAsState().value

    // putting all the necessary functions inside
    // our note screen
    NotesScreen(noteList = notesList,
        removeNote = {index ->
            noteViewModel.deleteNote(index)
            Log.d("remove invoked", "NotesApp: $index")
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