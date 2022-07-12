package com.example.jetnoteapp.repository

import com.example.jetnoteapp.data.NoteDatabaseDao
import com.example.jetnoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


// gonna inject dependency for our note dao

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.update(note)
    suspend fun deleteSingleNote(note: Note) = noteDatabaseDao.deleteSingleNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()

    // getting notes is a bit weird because of the coroutines flow
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getAllNotes()
        .flowOn(Dispatchers.IO)
        .conflate()

}