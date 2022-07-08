package com.example.jetnoteapp.data

import androidx.room.*
import com.example.jetnoteapp.model.Note

// now this is going to be dao(interface)
// with all the crud operations(add annotation)

@Dao
interface NoteDatabaseDao {

    // you can add manual queries
    // query to get all notes
    @Query("select * from notes_tbl")
    fun getAllNotes(): List<Note>

    // query to get note by id
    @Query("select * from notes_tbl where id=:id")
    fun getNoteById(id: String): Note

    // now in insert there is possibilities for errors
    // to happen in that case we gonna tell room database
    // to simply replace the note when encountering any errors
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("Delete from notes_tbl")
    fun deleteAll()

    // to delete one item
    @Delete
    fun deleteNote(note: Note)
    
}
