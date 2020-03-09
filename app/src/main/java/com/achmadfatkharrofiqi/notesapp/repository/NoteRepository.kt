package com.achmadfatkharrofiqi.notesapp.repository

import androidx.lifecycle.LiveData
import com.achmadfatkharrofiqi.notesapp.dao.NoteDao
import com.achmadfatkharrofiqi.notesapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
}