package com.geektech.notes.di

import android.content.Context
import androidx.room.Room
import com.geektech.notes.data.local.NoteDao
import com.geektech.notes.data.local.NoteDatabase
import com.geektech.notes.data.repository.NoteRepositoryImpl
import com.geektech.notes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): com.geektech.notes.data.local.NoteDatabase = Room.databaseBuilder(
        context,
        com.geektech.notes.data.local.NoteDatabase::class.java,
        "note_db"
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: com.geektech.notes.data.local.NoteDatabase) = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: com.geektech.notes.data.local.NoteDao): com.geektech.notes.domain.repository.NoteRepository =
        com.geektech.notes.data.repository.NoteRepositoryImpl(noteDao)

}