package com.example.jetnoteapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteapp.R
import com.example.jetnoteapp.components.NoteButton
import com.example.jetnoteapp.components.NoteInputText
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.util.formatDate

/*this is our notes screen, even though the app has
* only one screen we gonna make a separate package
* for it and we gonna defile the layout in here.
*
* make sure to add preview function to construct the layout*/


// in here we gonna pass a list of notes
// and lambda functions to remove and add notes


@Composable
fun NotesScreen( noteList: List<Note>,
                 addNote: (Note) -> Unit,
                 removeNote: (Note) -> Unit,
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
            verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp))

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

            NoteButton(text = "Save",
                modifier = Modifier.padding(10.dp),
                onClick = {
                /*little more validation here
                * gonna check if the title or description is not empty before
                * adding it our list
                * also gonna clear our text fields by setting them to empty string */

                if(titleText.value.isNotEmpty()) {

                    // here we are passing the data into the note object
                    // so it gets stored inside the list state we created
                    // in main activity

                    addNote(Note(title = titleText.value,
                        description = descriptionText.value
                        ))

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

            LazyColumn {

                itemsIndexed(noteList){ _, item ->
                    NoteRow(note = item,
                        onClickRow = { note ->
                            removeNote(note)
                            //Log.d("invoked here", "$note")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteRow( note: Note,
             modifier: Modifier = Modifier,
             onClickRow: (Note) -> Unit
) {

    val titleMaxLines = 1
    val descriptionMaxLines = 2

    // changing card to surface

    Surface(shape = RoundedCornerShape(topEnd = 10.dp),
        elevation = 4.dp,
        color = Color(0xFFB7DAFF),
        modifier = modifier
            .padding(10.dp)
            .clickable {
                onClickRow(note)
                //Log.d("in surface", "NoteRow: surface clicked")
            }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Text(text = note.title,
                style = MaterialTheme.typography.body2,
                maxLines = titleMaxLines,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = note.description,
                style = MaterialTheme.typography.body1,
                maxLines = descriptionMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                // this is the data time formatter, this how you use it in
                // jetpack compose

                // dot operator to get access to the type converter
                Text(text = formatDate(note.entryDate.time) ,
                    color = Color.DarkGray)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){

}