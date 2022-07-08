package com.example.jetnoteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*


// our data model


// now we gonna convert this data class into a room entity
// first we gonna use entity annotation and name our table

@Entity(tableName = "notes_tbl")
data class Note (


    // now all the parameters we wanna convert to columns
    // we can do this by using annotations again

    // UUID object generates unique id for our id's for note
    // the id is our primary key because it is unique
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    // now converting and naming the columns
    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_description")
    val description: String,

    // gets us the current date of device
    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date = Date.from(Instant.now())
)
