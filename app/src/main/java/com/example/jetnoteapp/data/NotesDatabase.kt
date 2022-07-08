package com.example.jetnoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetnoteapp.model.Note


// so this is our database extending it with room database
// also need to add database annotation and inside it we gonna
// pass our Data class entity and version


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    // here we creating a function that returns our
    // data base object (not yet created)

    abstract fun noteDao(): NoteDatabaseDao
}