package com.example.musicplayeritunessample.ui

import Results
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayeritunessample.Remote.TrackApi
import com.example.musicplayeritunessample.data.model.AppRepository
import com.example.musicplayeritunessample.data.model.Track
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    //
    val repository = AppRepository(TrackApi)
    val artistList = repository.track

    //    inputText für die such funktion
    val inputText = MutableLiveData<String>()

    private var _likedSongs = MutableLiveData<MutableList<Track>>(mutableListOf())
    val likedSongs: LiveData<MutableList<Track>>
        get() = _likedSongs


    private var _currentArtist = MutableLiveData<Track>()
    val currentArtist: LiveData<Track>
        get() = _currentArtist

    fun open(artist: Track) {
        _currentArtist.value = artist
        Log.d("Open", "Current : ${_currentArtist.value}  $this")
    }

    fun likedSong() {
        if (_currentArtist.value?.liked == true) {
            return
        } else {
            _currentArtist.value?.liked = true
            _likedSongs.value?.add(_currentArtist.value!!)
        }
    }

    fun disliked() {
        if (_currentArtist.value?.liked == false) {
            return
        } else {
            _currentArtist.value?.liked = false
            _likedSongs.value?.remove(_currentArtist.value!!)
        }
    }

    fun getResult(term: String) {
        viewModelScope.launch {
            repository.getTrack(term)
        }
    }


}