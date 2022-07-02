package com.example.jetnoteapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteapp.data.NoteDataSource
import com.example.jetnoteapp.model.Note
import java.time.format.DateTimeFormatter
import java.util.*

/*this function gives us a lot of info about
* the how can we use text fields as separate
* functions that we can later call anywhere*/

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    maxLine: Int = 1,
    // this tells us what should happen when we press
    // the ime button on keyboard we set it equal to
    // empty lambda function that does not return anything
    // doesn't take anything as it's parameter, just
    // executes the code inside the body
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        maxLines = maxLine,
        label = { Text(text = label) },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
       //     backgroundColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {onImeAction()
            keyboardController?.hide()}

        )
    )
}

//@Preview
@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {

    Button(onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(3.5.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF346DF8),
            contentColor = Color.White
        )
        ) {
        
        Text(text = text,
            style = MaterialTheme.typography.button
            )
    }
}

// TODO: maybe this onclick will be used to get data that we gonna wipe

@Composable
fun NoteRow( note: Note,
             modifier: Modifier = Modifier,
             onNoteRemove: (Note) -> Unit
             ) {

    val titleMaxLines = 1
    val descriptionMaxLines = 2

    // changing card to surface

    Surface(shape = RoundedCornerShape(topEnd = 10.dp),
        elevation = 4.dp,
        color = Color(0xFFB7DAFF),
        modifier = modifier.padding(10.dp)
            .clickable {
                onNoteRemove(note)
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

                Text(text = note.entryDate.
                format(DateTimeFormatter
                    .ofPattern("EEE, d MMM")),
                    color = Color.DarkGray)

            }
        }
    }
}
