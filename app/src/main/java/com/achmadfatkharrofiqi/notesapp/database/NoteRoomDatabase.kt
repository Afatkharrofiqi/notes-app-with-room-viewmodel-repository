package com.achmadfatkharrofiqi.notesapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.achmadfatkharrofiqi.notesapp.dao.NoteDao

abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instance of database opening at the same time
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteRoomDatabase::class.java,
                "note_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}