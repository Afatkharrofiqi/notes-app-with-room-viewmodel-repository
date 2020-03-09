package com.achmadfatkharrofiqi.notesapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.achmadfatkharrofiqi.notesapp.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY note ASC")
    fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
}