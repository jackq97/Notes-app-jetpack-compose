package com.example.jetnoteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteapp.R
import com.example.jetnoteapp.components.NoteInputText

/*this is our notes screen, even though the app has
* only one screen we gonna make a separate package
* for it and we gonna defile the layout in here.
*
* make sure to add preview function to construct the layout*/

@Composable
fun NotesScreen(){

    val titleText = remember {
        mutableStateOf("")
    }

    val descriptionText = remember {
        mutableStateOf("")
    }

    Column {

        /*there are two ways to pass info inside the top app bar
        * you can put data inside the top app bar or you define it
        * in arguments of top app bar*/

        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))},
            backgroundColor = Color.White,
            navigationIcon = { Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "go back")}
            )

        Column {

            NoteInputText(text = titleText.value,
                onTextChange = {
                    "it = titleText.value"
                }, label = "Title")

            NoteInputText(text = descriptionText.value,
                onTextChange = {
                    "it = titleText.value"
                }, label = "Description")


        }
    }

}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NotesScreen()
}