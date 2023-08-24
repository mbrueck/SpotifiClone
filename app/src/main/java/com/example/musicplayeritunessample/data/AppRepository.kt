package com.example.musicplayeritunessample.data.model


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.Remote.TrackApi

class AppRepository(private val api : TrackApi) {

    fun loadArtist(): MutableList<Track>{

        return mutableListOf(
            Track(artistName = "Kool Savas", trackName = "Beste Tag meines Lebens", previewUrl = "https//", artwork = "", trackTimeMillis = 0, kind = ""),
            Track(artistName = "Sido", trackName = "Ich und miene Maske", previewUrl = "https//", artwork = "", trackTimeMillis = 0, kind = ""),
            Track(artistName = "haiyti", trackName = "Akku", previewUrl = "https//", artwork = "", trackTimeMillis = 0, kind = ""),
            Track(artistName = "Materia", trackName = "Lila Wolken", previewUrl = "https//", artwork = "", trackTimeMillis = 0, kind = ""),
            Track(artistName = "KIZ", trackName = "Hurensohn", previewUrl = "https//", artwork = "", trackTimeMillis = 0, kind = ""),
            Track(artistName = "Audio88 & Yassin", trackName = "Täter oder Opfer", previewUrl = "https//", artwork = "", trackTimeMillis = 0, kind = ""),

            )
    }
    private val _track = MutableLiveData<List<Track>>()
    val track : LiveData<List<Track>>
        get() = _track
    suspend fun getTrack(term:String){
        try {
            _track.value = api.retrofitService.getTrack(term).results

        } catch (e:Exception){
            Log.e("Repo","ERROR, LOADING DATA FAILED : $e")
        }
    }


}