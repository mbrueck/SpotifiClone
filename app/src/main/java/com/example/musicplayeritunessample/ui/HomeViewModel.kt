package com.example.musicplayeritunessample.ui

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
    val artistList = repository.search
    val mainSideList = repository.trackList

    //    inputText für die such funktion
    val inputText = MutableLiveData<String>()


//    Live Data zur gelikten Songs
    private var _likedSongs = MutableLiveData<MutableList<Track>>(mutableListOf())
    val likedSongs: LiveData<MutableList<Track>>
        get() = _likedSongs

//  Live Data zur
    private var _currentArtist = MutableLiveData<Track>()
    val currentArtist: LiveData<Track>
        get() = _currentArtist

    private val _genre = MutableLiveData<String>("")
    val genre : LiveData<String>
        get() = _genre


    fun open(artist: Track) {
        _currentArtist.value = artist
        Log.d("Open", "Current : ${_currentArtist.value}  $this")
    }

    fun likedSong() {
       try {
           if (_currentArtist.value?.liked == true) {
               return
           } else {
               _currentArtist.value?.liked = true
               _likedSongs.value?.add(_currentArtist.value!!)
           }
       } catch (e : Exception){
           Log.e("Like Button","ERROR, LOADING DATA FAILED : $e")
       }
    }

    fun disliked() {
       try {
           if (_currentArtist.value?.liked == false) {
               return
           } else {
               _currentArtist.value?.liked = false
               _likedSongs.value?.remove(_currentArtist.value!!)
           }
       } catch (e: Exception){
           Log.e("Dislike Button","ERROR, LOADING DATA FAILED : $e")
       }
    }

    fun getResult(term: String) {
        viewModelScope.launch {
            repository.getSearch(term)
            Log.d("Result Search", "Data : $term")
        }
    }

    fun getTrackList() {
        val term :String = genreMap.keys.random()
        _genre.value = term
        val id : String = genreMap[term]!!
        viewModelScope.launch {
            repository.getTrackList("ab",id)
            Log.d("Result Tracklist", "Data : $term")

        }
    }

    private val genreMap = mapOf(
        "Country" to "6",
        "Pop" to "14",
        "Rock" to "21",
        "Hip-Hop/Rap" to "18",
        "R&B/Soul" to "15",
        "Metal" to "1153",
        "Blues" to "2",
        "Jazz" to "11",
        "Electronic" to "7",
        "Alternative" to "20"
    )
}