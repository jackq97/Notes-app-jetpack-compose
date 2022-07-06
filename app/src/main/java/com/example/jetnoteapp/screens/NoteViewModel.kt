package com.example.jetnoteapp.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnoteapp.data.NoteDataSource
import com.example.jetnoteapp.model.Note

// view model should be close to the main screen
// and it is a good practice to append view model
// with the name of screen that's gonna use it's data

// there can be more than one view model that's why it
// is good to name it so we can differentiate.

class NoteViewModel: ViewModel() {

    // seems like we gonna pass note related lists, variables and
    // functions (that count as logic maybe ?) inside the view model
    // so all the operations that we gonna perform on view model

    // our note list
    private val noteList = mutableStateListOf<Note>()


    init {
        // doing this so we have some data to work with
      noteList.addAll(NoteDataSource().loadData())
    }

    // fun to add note
    fun addNote(note: Note){
        noteList.add(note)
    }

    // fun to remove note
    fun removeNote(index: Int){
        Log.d("view model",
            "removeNote: invoked from view mode $index")
        noteList.removeAt(index)
    }

    // fun to get the list
    fun getNoteList(): List<Note>{
        return noteList
    }

}
