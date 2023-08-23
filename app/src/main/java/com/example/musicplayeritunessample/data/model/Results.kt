import com.squareup.moshi.Json

data class Results(
    @Json(name = "artistName") val artistName: String ,
    @Json(name = "trackName") val trackName: String = "",
    @Json(name = "previewUrl") val previewUrl: String ,
    @Json(name = "artworkUrl100") val artwork: Int,
    @Json(name = "trackTimeMillis") val trackTimeMillis: Int = 0,
    @Json(name = "kind") val kind: String,
) {
    val trackTimeSeconds = trackTimeMillis / 1000
    var liked: Boolean = false
    var disliked: Boolean = false
}