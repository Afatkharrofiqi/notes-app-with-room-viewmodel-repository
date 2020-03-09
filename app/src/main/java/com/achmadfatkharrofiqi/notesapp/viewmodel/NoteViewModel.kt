package com.achmadfatkharrofiqi.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.achmadfatkharrofiqi.notesapp.database.NoteRoomDatabase
import com.achmadfatkharrofiqi.notesapp.model.Note
import com.achmadfatkharrofiqi.notesapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository

    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    // do insert note in the background thread
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
}