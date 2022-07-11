package com.example.jetnoteapp.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnoteapp.data.NoteDataSource
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

// view model should be close to the main screen
// and it is a good practice to append view model
// with the name of screen that's gonna use it's data

// there can be more than one view model that's why it
// is good to name it so we can differentiate.

// ( now view model will access our database

// injecting our repository in our view model so we can access our dao

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    // seems like we gonna pass note related lists, variables and
    // functions (that count as logic maybe ?) inside the view model
    // so all the operations that we gonna perform on view model

    // our note list
    // we need to convert this one to mutable state of flow
    // private val noteList = mutableStateListOf<Note>()

    // this is how we define mutable state  flow we pass in the list
    // and the initialize it with empty list method
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())

    // so we gonna create two note lists for our app, first one with underscore
    // is a private list to only use within this class for it's functions
    // we gonna make another list that's gonna use it's state

    val noteList = _noteList.asStateFlow()


    init {
        // now to add notes from our data base flow state , it is a bir different
        // again we will have to launch view model scope and first we gonna collect
        // the list from our flow state

       viewModelScope.launch(Dispatchers.IO) {
           // this is how you retrieve a list from state flow
           repository.getAllNotes().distinctUntilChanged()
               .collect{ listOfNotes ->
                   // lambda for our notes
                   if (listOfNotes.isEmpty()){
                       // means list is empty
                       Log.d("view model", "empty: empty list")
                   } else {
                       // means list is not empty so we gotta populate our note list here
                       _noteList.value = listOfNotes
                   }
               }
       }
    }

    // crud operations in view mode to update states

    // perfect example of utilising coroutines and repository
    // the view model scope is used to work with room data
    // dao functions so we don't need to manually launch
    // operations in ui thread with coroutines scopes
    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteByIdNote(id: String) = viewModelScope.launch {
        repository.deleteNoteById(id)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

}
