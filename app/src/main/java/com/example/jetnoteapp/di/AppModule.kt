package com.example.jetnoteapp.di

import android.content.Context
import androidx.room.Room
import com.example.jetnoteapp.data.NoteDatabaseDao
import com.example.jetnoteapp.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// package should be lower case

// so for our data base we gonna need a builder
// because we don't want the app to create multiple
// instances of the data base when we launch the app

// that's why we gonna define it as a singleton object
// and add hilt annotations to it

// this objects will add bindings to the hilt

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    // singleton function that provides(returns) us with the notes data base object
    // first takes notes data base as a parameter

    @Singleton
    @Provides
    fun providesNotesDao(notesDataBase: NotesDatabase): NoteDatabaseDao
    = notesDataBase.noteDao()


    // now to actually build our data base

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): NotesDatabase
    = Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}