package com.example.jetnoteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// first we extend this class to application
// which means this class has access to all
// the app and eco system
// then we use hilt annotation so hilt can
// access all the app resources

@HiltAndroidApp
class NoteApplication: Application() {
}