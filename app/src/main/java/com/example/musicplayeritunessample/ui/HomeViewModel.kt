package com.example.musicplayeritunessample.ui

import Results
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayeritunessample.data.model.AppRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val artistList = AppRepository().loadArtist()
    //    inputText für die such funktion
    val inputText = MutableLiveData<String>()


    private var _currentArtist = MutableLiveData<Results>()
    val currentArtist: LiveData<Results>
        get() = _currentArtist

    fun open(artist : Results){
        _currentArtist.value = artist
        Log.d("Open","Current : ${_currentArtist.value}")
    }
    fun addLikedSong(){
        _currentArtist.value?.liked = true
    }
    fun disliked(){
        _currentArtist.value?.liked = false
    }

    fun test (){
        _currentArtist.value = _currentArtist.value
    }


}