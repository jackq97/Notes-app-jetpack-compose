package com.example.jetnoteapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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

}