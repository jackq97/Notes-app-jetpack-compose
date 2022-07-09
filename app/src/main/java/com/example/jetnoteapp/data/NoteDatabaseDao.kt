package com.example.jetnoteapp.data

import androidx.room.*
import com.example.jetnoteapp.model.Note
import kotlinx.coroutines.flow.Flow

// now this is going to be dao(interface)
// with all the crud operations(add annotation)

//                 coroutines
// suspend keyword -> making the functions suspendable so
// they can be suspended and started somewhere else ( not
// on a ui thread )

@Dao
interface NoteDatabaseDao {

    // you can add manual queries
    // query to get all notes
    @Query("select * from notes_tbl")
    fun getAllNotes(): Flow<List<Note>> // we will be using flow for this one (no need for suspend)

    // query to get note by id
    @Query("select * from notes_tbl where id=:id")
    suspend fun getNoteById(id: String): Note

    // now in insert there is possibilities for errors
    // to happen in that case we gonna tell room database
    // to simply replace the note when encountering any errors
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("Delete from notes_tbl")
    suspend fun deleteAll()

    // to delete one item
    @Delete
    suspend fun deleteNote(note: Note)

}
