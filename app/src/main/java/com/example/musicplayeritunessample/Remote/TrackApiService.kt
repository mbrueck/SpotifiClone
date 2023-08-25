package com.example.musicplayeritunessample.Remote


import Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://itunes.apple.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TrackApiService{
    @GET("search?media=music")
    suspend fun getSearch(
        @Query("term") term:String,
    ):Results

    @GET("search?media=music")
    suspend fun getTrackList(
        @Query("term") term:String,
        @Query("genreId") genreId: String

    ) : Results

}

object TrackApi{
    val retrofitService: TrackApiService by lazy { retrofit.create(TrackApiService::class.java) }
}






