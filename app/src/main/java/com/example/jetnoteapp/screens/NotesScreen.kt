package com.example.jetnoteapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteapp.R
import com.example.jetnoteapp.components.NoteButton
import com.example.jetnoteapp.components.NoteInputText
import com.example.jetnoteapp.components.NoteRow
import com.example.jetnoteapp.data.NoteDataSource
import com.example.jetnoteapp.model.Note

/*this is our notes screen, even though the app has
* only one screen we gonna make a separate package
* for it and we gonna defile the layout in here.
*
* make sure to add preview function to construct the layout*/


// in here we gonna pass a list of notes
// and lambda functions to remove and add notes


@Composable
fun NotesScreen( noteList: List<Note>,
                 removeNote: (Note) -> Unit,
                 addNote: (Note) -> Unit
                 ){

    // context
    val context = LocalContext.current

    val titleText = remember {
        mutableStateOf("")
    }

    val descriptionText = remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {

        /*there are two ways to pass info inside the top app bar
       * you can put data inside the top app bar or you define it
       * in arguments of top app bar*/

        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))},
            backgroundColor = Color.White,
            navigationIcon = { Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "go back")}

        ) }) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize())

        {
            NoteInputText(text = titleText.value,
                onTextChange = {

                    // this code here checks if the entry
                    // is validated only then it updates
                    // the text field

                    if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                        }) titleText.value = it

                }, label = "Title",
            modifier = Modifier.padding(top = 9.dp,
            bottom = 8.dp))

            NoteInputText(text = descriptionText.value,
                onTextChange = {

                    // this code here checks if the entry
                    // is validated only then it updates
                    // the text field

                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) descriptionText.value = it

                }, label = "Description")

            NoteButton(text = "Save", onClick = {
                /*little more validation here
                * gonna check if the title or description is not empty before
                * adding it our list
                * also gonna clear our text fields by setting them to empty string */

                if(titleText.value.isNotEmpty()) {
                    // we gonna save here and clear the fields
                    titleText.value = ""
                    descriptionText.value = ""

                    // now here we will display toast to show user that data
                    // has been saved
                    // so about context, since we in a kotlin file we can not
                    // get a context, to solve get we gonna make a variable
                    // that gets us the context of the activity where this
                    // function has been called
                    Toast.makeText(context, "Entry saved",
                        Toast.LENGTH_SHORT).show()
                }
            })

            Divider()

            LazyColumn() {

                items(items = NoteDataSource().loadData()){ noteList ->
                    NoteRow(note = noteList)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NotesScreen(noteList = emptyList(), {},{})
}