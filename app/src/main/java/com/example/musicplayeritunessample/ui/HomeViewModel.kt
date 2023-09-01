package com.example.musicplayeritunessample.ui

import android.app.Application
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.musicplayeritunessample.Remote.TrackApi
import com.example.musicplayeritunessample.data.local.getDatabase
import com.example.musicplayeritunessample.data.model.AppRepository
import com.example.musicplayeritunessample.data.model.Track
import kotlinx.coroutines.launch

private val TAG = "HOMEVIEWMODEL"

enum class MediaStatus { LOADING, READY, PLAYING, FINISHED }
class HomeViewModel(application: Application) :
    AndroidViewModel(application) {

    private val database = getDatabase(application)
    val repository = AppRepository(TrackApi, database)
    val artistList = repository.search
    val mainSideList = repository.trackList
    var mediaPlayer = MediaPlayer()


    // update livedata Timestatus ( miniPlayer)
    private val _playerStatus = MutableLiveData<MediaStatus>()
    val playerStatus: LiveData<MediaStatus>
        get() = _playerStatus

    private val _songTime = MutableLiveData<Int>()
    val songTime: LiveData<Int>
        get() = _songTime

    private val _selectedSong = MutableLiveData<Track>()
    val selectedSong: LiveData<Track>
        get() = _selectedSong


    //    inputText für die such funktion
    val inputText = MutableLiveData<String>()

    //    Live Data zur gelikten Songs
//    private var _likedSongs = MutableLiveData<MutableList<Track>>(mutableListOf())
    val likedSongs = repository.likedSongList


    //  Live Data zum Laden der Daten im PLayer bereich
    private var _currentArtist = MutableLiveData<Track>()
    val currentArtist: LiveData<Track>
        get() = _currentArtist

    private val _genre = MutableLiveData("")
    val genre: LiveData<String>
        get() = _genre


    fun open(artist: Track) {
        _currentArtist.value = artist
        Log.d("Open", "Current : ${_currentArtist.value}  $this")
    }

    fun likedSong() {
        viewModelScope.launch {
            _currentArtist.value?.liked = true
            _currentArtist.value?.disliked = false
            repository.insert(_currentArtist.value!!)
        }
    }

    fun disliked() {
        viewModelScope.launch {
            _currentArtist.value?.disliked = true
            _currentArtist.value?.liked = false
            _currentArtist.value?.let { repository.delete(it.id) }
        }
    }

    // Result by inputText / SearchBar
    fun getResult(term: String) {
        viewModelScope.launch {
            repository.getSearch(term)
            Log.d("Result Search", "Data : $term")
        }
    }


    fun getTrackList() {
        val term: String = genreMap.keys.random()
        _genre.value = term
        val id: String = genreMap[term]!!
        viewModelScope.launch {
            repository.getTrackList("BC", id)
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

    var updateSongTime = object : Runnable {
        override fun run() {
            _songTime.value = mediaPlayer.currentPosition
            android.os.Handler().postDelayed(this, 1000)
        }
    }

    fun setupMediaPlayer() {
        mediaPlayer.setOnPreparedListener(OnPreparedListener {
            Log.e("ViewModel", "MediaReady! ${mediaPlayer.duration}")
            _playerStatus.value = MediaStatus.READY
            mediaPlayer.start()
            _playerStatus.value = MediaStatus.PLAYING
            updateSongTime.run()
        })
        mediaPlayer.setOnCompletionListener {
            _playerStatus.value = MediaStatus.FINISHED
        }
    }

    fun startNewSong() {
        mediaPlayer.reset()
        try {
            _selectedSong.value = _currentArtist.value
            if (_playerStatus.value == MediaStatus.PLAYING) {
                mediaPlayer = MediaPlayer()
            }
            setupMediaPlayer()
            _playerStatus.value = MediaStatus.LOADING
            mediaPlayer.setDataSource(selectedSong.value?.previewUrl)
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            Log.e("MediaPLayer", "Error, player not initialised :$e ")
        }
    }

    fun playOrPauseSong() {
        try {
            if (_playerStatus.value == MediaStatus.PLAYING) {
                mediaPlayer.pause()
                _playerStatus.value = MediaStatus.READY
            } else {
                mediaPlayer.start()
                _playerStatus.value = MediaStatus.PLAYING
            }
        } catch (e: Exception) {
            Log.d(TAG, "play ore pause :$e")
        }
    }
    fun skipForward() {
        try {
            mediaPlayer.seekTo(mediaPlayer.currentPosition + 5000)
        } catch (e: Exception) {
            Log.e(TAG, "Error, skip forwoard :$e")
        }
    }
    fun skipBackward() {
        try {
            mediaPlayer.seekTo(mediaPlayer.currentPosition - 5000)
        } catch (e: Exception) {
            Log.e(TAG, "Error, skip backwoard :$e")

        }
    }
}
