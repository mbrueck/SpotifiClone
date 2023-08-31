package com.example.musicplayeritunessample.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.musicplayeritunessample.data.model.Track


@Dao
interface SongDatabaseDao {
    @Insert
    suspend fun insert(result: Track)

    @Update
    suspend fun update(result: Track)

    @Query("SELECT*FROM Track")
    fun getAll(): LiveData<List<Track>>

    @Query("DELETE FROM Track WHERE id = :key")
    suspend fun deleteById(key: Long)
}