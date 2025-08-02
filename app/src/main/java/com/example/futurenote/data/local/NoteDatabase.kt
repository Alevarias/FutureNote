package com.example.futurenote.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    // Straight up looked this up. Still trying to figure out all the parts.
    companion object {
        @Volatile private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                                context.applicationContext,
                                NoteDatabase::class.java,
                                "note_database"
                            ).fallbackToDestructiveMigration(false).build().also { INSTANCE = it }
            }
        }
    }
}