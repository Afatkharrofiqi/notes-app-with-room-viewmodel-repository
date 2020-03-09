package com.achmadfatkharrofiqi.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.achmadfatkharrofiqi.notesapp.dao.NoteDao
import com.achmadfatkharrofiqi.notesapp.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    private class NoteDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.noteDao())
                }
            }
        }

        suspend fun populateDatabase(noteDao: NoteDao) {
            // Delete All Notes
            noteDao.deleteAll()

            // Add simple notes.
            noteDao.insert(Note("Hello this is my first note"))

            noteDao.insert(Note("Hello this is my second note"))
        }
    }

    companion object {
        // Singleton prevents multiple instance of database opening at the same time
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database"
                )
                .addCallback(NoteDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}