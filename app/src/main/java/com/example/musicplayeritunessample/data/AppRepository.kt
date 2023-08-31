package com.example.musicplayeritunessample.data.model


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicplayeritunessample.Remote.TrackApi
import com.example.musicplayeritunessample.data.local.SongDatabase

val TAG = "AppRepository"

class AppRepository(private val api: TrackApi, private val databse: SongDatabase) {

    fun loadArtist(): MutableList<Track> {

        return mutableListOf(
            Track(
                artistName = "Kool Savas",
                trackName = "Beste Tag meines Lebens",
                previewUrl = "https//",
                artwork = "",
                trackTimeMillis = 0,
                kind = ""
            ),
            Track(
                artistName = "Sido",
                trackName = "Ich und miene Maske",
                previewUrl = "https//",
                artwork = "",
                trackTimeMillis = 0,
                kind = ""
            ),
            Track(
                artistName = "haiyti",
                trackName = "Akku",
                previewUrl = "https//",
                artwork = "",
                trackTimeMillis = 0,
                kind = ""
            ),
            Track(
                artistName = "Materia",
                trackName = "Lila Wolken",
                previewUrl = "https//",
                artwork = "",
                trackTimeMillis = 0,
                kind = ""
            ),
            Track(
                artistName = "KIZ",
                trackName = "Hurensohn",
                previewUrl = "https//",
                artwork = "",
                trackTimeMillis = 0,
                kind = ""
            ),
            Track(
                artistName = "Audio88 & Yassin",
                trackName = "Täter oder Opfer",
                previewUrl = "https//",
                artwork = "",
                trackTimeMillis = 0,
                kind = ""
            ),

            )
    }

    // Results of search/ library
    private val _search = MutableLiveData<List<Track>>()
    val search: LiveData<List<Track>>
        get() = _search

    suspend fun getSearch(term: String) {
        try {
            _search.value = api.retrofitService.getSearch(term).results

        } catch (e: Exception) {
            Log.e("Repo", "ERROR, LOADING DATA FAILED : $e")
        }
    }

    //  List for Mainpage

    private val _trackList = MutableLiveData<List<Track>>()
    val trackList: LiveData<List<Track>>
        get() = _trackList

    suspend fun getTrackList(term: String, id: String) {
        try {
            _trackList.value = api.retrofitService.getTrackList(term, id).results
        } catch (e: Exception) {
            Log.e("Repo", "ERROR, LOADING DATA FAILED : $e")
        }
    }

//    LikedSongList

    val likedSongList: LiveData<List<Track>> = databse.songDatabaseDao.getAll()

    suspend fun insert(track: Track) {
        try {
            databse.songDatabaseDao.insert(track)

        } catch (e: Exception) {
            Log.d(TAG, "Fehler beim einfügen in die Database :$e")
        }
    }

    suspend fun delete(key: Long) {
        try {
            databse.songDatabaseDao.deleteById(key)
        } catch (e: Exception) {
            Log.d(TAG, "Fehler beim entfernen der Daten aus der Database :$e")

        }
    }


}