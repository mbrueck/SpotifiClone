package com.example.musicplayeritunessample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.musicplayeritunessample.data.model.Track

@Database(entities = [Track::class], version = 1)
abstract class SongDatabase : RoomDatabase() {

    abstract val songDatabaseDao: SongDatabaseDao
}


private lateinit var INSTANCE: SongDatabase

fun getDatabase(context: Context): SongDatabase {
    synchronized(SongDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SongDatabase::class.java,
                "song_database"
            )
                .build()
        }
    }
    return INSTANCE
}