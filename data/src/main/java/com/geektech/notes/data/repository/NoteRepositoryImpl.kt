package com.geektech.notes.data.repository

import com.geektech.notes.data.base.BaseRepository
import com.geektech.notes.data.local.NoteDao
import com.geektech.notes.data.mapper.toEntity
import com.geektech.notes.data.mapper.toNote
import com.geektech.notes.domain.model.Note
import com.geektech.notes.domain.repository.NoteRepository
import com.geektech.notes.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(

    private val noteDao: NoteDao

) : BaseRepository(), NoteRepository {
    override fun getAllNotes(): Flow<Resource<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun createNote(note: Note) = doRequest {
        noteDao.createNotes(note.toEntity())
    }

    override fun updateNote(note: Note) = doRequest {
        noteDao.updateNotes(note.toEntity())
    }

    override fun deleteNote(note: Note) = doRequest {
        noteDao.deleteNotes(note.toEntity())
    }
}