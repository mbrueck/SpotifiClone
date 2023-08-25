package com.example.musicplayeritunessample.data.model


import Results
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.Remote.TrackApi

class AppRepository(private val api: TrackApi) {

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

    suspend fun getTrackList(term: String, id:String) {
        try {
            _trackList.value = api.retrofitService.getTrackList(term,id).results
        } catch (e: Exception) {
            Log.e("Repo", "ERROR, LOADING DATA FAILED : $e")
        }
    }
}