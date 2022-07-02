package com.example.jetnoteapp.model

import java.time.LocalDateTime
import java.util.*


// our data model

data class Note (
    // UUID object generates unique id for our id's for note
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    // gets us the current date of device
    val entryDate: LocalDateTime = LocalDateTime.now())