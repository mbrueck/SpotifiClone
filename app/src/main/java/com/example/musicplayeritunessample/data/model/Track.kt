package com.example.musicplayeritunessample.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Track(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Json(name = "artistName") val artistName: String,
    @Json(name = "trackName") val trackName: String = "",
    @Json(name = "previewUrl") val previewUrl: String,
    @Json(name = "artworkUrl100") val artwork: String,
    @Json(name = "trackTimeMillis") val trackTimeMillis: Int = 0,
    @Json(name = "kind") val kind: String,
) {
    var trackTimeSeconds = trackTimeMillis / 1000
    var liked: Boolean = false
    var disliked: Boolean = false
}