package com.example.jetnoteapp.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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