package com.example.jetnoteapp.data

import com.example.jetnoteapp.model.Note


class NoteDataSource() {

    fun loadData(): List<Note> {
        return listOf(

            Note(title = "A good day", description = "We went on a vacation by the lake"),
            Note(title = "Android Compose", description = "Working on Android Compose course today"),
            Note(title = "Keep at it...", description = "Sometimes things just happen"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Android Studio brings a lot of new features specifically for Jetpack Compose. It embraces a code-first approach while improving the developer productivity without having to choose between design interface or code editor only."),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family")

        )
    }

}