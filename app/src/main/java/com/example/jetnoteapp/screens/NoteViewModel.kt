package com.example.jetnoteapp.screens

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
    private val noteList = mutableListOf<Note>()


    init {
        // doing this so we have some data to work with
        noteList.addAll(NoteDataSource().loadData())
    }

    // fun to add note
    fun addNote(note: Note){
        noteList.add(note)
    }

    // fun to remove note
    fun removeNote(note: Note){
        noteList.remove(note)
    }

    // fun to get the list
    @JvmName("getNoteList1")
    fun getNoteList(): List<Note>{
        return noteList
    }

}
